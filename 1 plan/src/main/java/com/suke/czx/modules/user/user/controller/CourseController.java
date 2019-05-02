package com.suke.czx.modules.user.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.suke.czx.modules.app.annotation.AuthIgnore;
import com.suke.czx.modules.user.user.entity.CareerEntity;
import com.suke.czx.modules.user.user.entity.CourseEntity;
import com.suke.czx.modules.user.user.service.CourseService;
import com.suke.czx.common.utils.PageUtils;
import com.suke.czx.common.utils.Query;
import com.suke.czx.common.utils.R;




/**
 * course
 * 
 * @author czx
 * @email object_czx@163.com
 * @date 2019-04-30 19:11:47
 */
@RestController
@RequestMapping("/user/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:course:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        List<CourseEntity> courseList = courseService.queryList(query);
        int total = courseService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(courseList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

	/**
	 * 课程列表
	 */
    @CrossOrigin(origins = {"http://localhost:8090", "null"})
    @AuthIgnore
    @GetMapping("listAll")
	public R listAll(){
		Map<String, Object> map = new HashMap<>();
		
		List<CourseEntity> list = courseService.queryList(map);
		
		return R.ok().put("list", list);
	}
    /**
     * 信息
     */
    @RequestMapping("/info/{courseId}")
    @RequiresPermissions("user:course:info")
    public R info(@PathVariable("courseId") Integer courseId){
			CourseEntity course = courseService.queryObject(courseId);

        return R.ok().put("course", course);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:course:save")
    public R save(@RequestBody CourseEntity course){
			courseService.save(course);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:course:update")
    public R update(@RequestBody CourseEntity course){
			courseService.update(course);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:course:delete")
    public R delete(@RequestBody Integer[] courseIds){
			courseService.deleteBatch(courseIds);

        return R.ok();
    }
	
}
