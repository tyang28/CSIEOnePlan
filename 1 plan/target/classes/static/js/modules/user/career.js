$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'user/career/list',
        datatype: "json",
        colModel: [			
			{ label: 'careerId', name: 'careerId', index: 'career_id', width: 50, key: true },
			{ label: 'name', name: 'name', index: 'name', width: 80 }, 			
			{ label: 'detail', name: 'detail', index: 'detail', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		career: {skillIdList:[]},
		skillList: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "add";
			vm.career = {skillIdList:[]};
			vm.skillList = {};
			this.getSkillList();
		},
		update: function (event) {
			var careerId = getSelectedRow();
			if(careerId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "update";
            this.getSkillList();
            vm.getInfo(careerId);
		},
		saveOrUpdate: function (event) {
			console.log(vm.career)
			var skillId = "";
			for(var index in vm.career.skillIdList){
				if(index == (vm.career.skillIdList.length-1)){
					skillId += vm.career.skillIdList[index]
				}else {
					skillId += vm.career.skillIdList[index] + ","
				}
				
				
			}
			if(vm.career.careerId == null ){
				var career = {name:vm.career.name,skillId:skillId,detail:vm.career.detail}
			}else {
				var career = {name:vm.career.name,skillId:skillId,careerId:vm.career.careerId,detail:vm.career.detail}
			}
			var url = vm.career.careerId == null ? "user/career/save" : "user/career/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(career),
			    success: function(r){
			    	if(r.code === 0){
						alert('Successful operation', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var careerIds = getSelectedRows();
			if(careerIds == null){
				return ;
			}
			
			confirm('Make sure you want to delete the selected record？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "user/career/delete",
                    contentType: "application/json",
				    data: JSON.stringify(careerIds),
				    success: function(r){
						if(r.code == 0){
							alert('Successful operation', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(careerId){
			$.get(baseURL + "user/career/info/"+careerId, function(r){
				r.career.skillIdList = r.career.skillId.split(",")
				console.log(r.career)
                vm.career = r.career;
            });
		},
		getSkillList: function(){
			$.get(baseURL + "user/skill/select", function(r){
				vm.skillList = r.list;
				console.log(r.list)
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});