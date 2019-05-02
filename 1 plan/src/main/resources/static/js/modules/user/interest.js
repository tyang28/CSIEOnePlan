$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'user/interest/list',
        datatype: "json",
        colModel: [			
			{ label: 'interId', name: 'interId', index: 'inter_id', width: 50, key: true },
			{ label: 'name', name: 'name', index: 'name', width: 80 }, 	
			
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
		interest: {skillIdList:[]},
		skillList: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "add";
			vm.interest = {skillIdList:[]};
			vm.skillList = {};
			this.getSkillList();
		},
		update: function (event) {
			var interId = getSelectedRow();
			if(interId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "update";
            this.getSkillList();
            vm.getInfo(interId);
		},
		saveOrUpdate: function (event) {
			console.log(vm.interest)
			var skillId = "";
			for(var index in vm.interest.skillIdList){
				if(index == (vm.interest.skillIdList.length-1)){
					skillId += vm.interest.skillIdList[index]
				}else {
					skillId += vm.interest.skillIdList[index] + ","
				}
				
				
			}
			if(vm.interest.interId == null ){
				var interst = {name:vm.interest.name,skillId:skillId}
			}else {
				var interst = {name:vm.interest.name,skillId:skillId,interId:vm.interest.interId}
			}
		
			var url = vm.interest.interId == null ? "user/interest/save" : "user/interest/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(interst),
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
			var interIds = getSelectedRows();
			if(interIds == null){
				return ;
			}
			
			confirm('Make sure you want to delete the selected record？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "user/interest/delete",
                    contentType: "application/json",
				    data: JSON.stringify(interIds),
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
		getInfo: function(interId){
			$.get(baseURL + "user/interest/info/"+interId, function(r){
				r.interest.skillIdList = r.interest.skillId.split(",")
				console.log(r.interest)
                vm.interest = r.interest;
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