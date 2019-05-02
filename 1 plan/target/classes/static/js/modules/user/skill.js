$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'user/skill/list',
        datatype: "json",
        colModel: [			
			{ label: 'skillId', name: 'skillId', index: 'skill_id', width: 50, key: true },
			{ label: 'name', name: 'name', index: 'name', width: 80 }			
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
		skill: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "add";
			vm.skill = {};
		},
		update: function (event) {
			var skillId = getSelectedRow();
			if(skillId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "update";
            
            vm.getInfo(skillId)
		},
		saveOrUpdate: function (event) {
			var url = vm.skill.skillId == null ? "user/skill/save" : "user/skill/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.skill),
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
			var skillIds = getSelectedRows();
			if(skillIds == null){
				return ;
			}
			
			confirm('Make sure you want to delete the selected record？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "user/skill/delete",
                    contentType: "application/json",
				    data: JSON.stringify(skillIds),
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
		getInfo: function(skillId){
			$.get(baseURL + "user/skill/info/"+skillId, function(r){
				console.log(baseURL + "user/skill/info/"+skillId)
                vm.skill = r.skill;
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