$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'user/course/list',
        datatype: "json",
        colModel: [			
			{ label: 'courseId', name: 'courseId', index: 'course_id', width: 50, key: true },
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
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		course: {skillIdList:[]},
		skillList: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "add";
			vm.course = {skillIdList:[]};
			vm.skillList = {};
			this.getSkillList();
		},
		update: function (event) {
			var courseId = getSelectedRow();
			if(courseId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "update";
            this.getSkillList();
            vm.getInfo(courseId);
		},
		saveOrUpdate: function (event) {
			console.log(vm.course)
			var skillId = "";
			for(var index in vm.course.skillIdList){
				if(index == (vm.course.skillIdList.length-1)){
					skillId += vm.course.skillIdList[index]
				}else {
					skillId += vm.course.skillIdList[index] + ","
				}
				
				
			}
			if(vm.course.courseId == null ){
				var course = {name:vm.course.name,skillId:skillId}
			}else {
				var course = {name:vm.course.name,skillId:skillId,courseId:vm.course.courseId}
			}
			var url = vm.course.courseId == null ? "user/course/save" : "user/course/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(course),
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
			var courseIds = getSelectedRows();
			if(courseIds == null){
				return ;
			}
			
			confirm('Make sure you want to delete the selected record？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "user/course/delete",
                    contentType: "application/json",
				    data: JSON.stringify(courseIds),
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
		getInfo: function(courseId){
			$.get(baseURL + "user/course/info/"+courseId, function(r){
				r.course.skillIdList = r.course.skillId.split(",")
				console.log(r.course)
                vm.course = r.course;
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