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
import com.suke.czx.modules.user.user.entity.CourseEntity;
import com.suke.czx.modules.user.user.entity.InterestEntity;
import com.suke.czx.modules.user.user.service.InterestService;
import com.suke.czx.common.utils.PageUtils;
import com.suke.czx.common.utils.Query;
import com.suke.czx.common.utils.R;




/**
 * interst
 * 
 * @author czx
 * @email object_czx@163.com
 * @date 2019-04-29 15:56:17
 */
@RestController
@RequestMapping("/user/interest")
public class InterestController {
    @Autowired
    private InterestService interestService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequestMapping("/list")
    @RequiresPermissions("user:interest:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        List<InterestEntity> interestList = interestService.queryList(query);
        int total = interestService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(interestList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

	/**
	 * 兴趣列表
	 */
    @CrossOrigin(origins = {"http://localhost:8090", "null"})
    @AuthIgnore
    @GetMapping("listAll")
	public R listAll(){
		Map<String, Object> map = new HashMap<>();
		
		List<InterestEntity> list = interestService.queryList(map);
		
		return R.ok().put("list", list);
	}
    /**
     * 信息
     */
    @RequestMapping("/info/{interId}")
    @RequiresPermissions("user:interest:info")
    public R info(@PathVariable("interId") Integer interId){
			InterestEntity interest = interestService.queryObject(interId);

        return R.ok().put("interest", interest);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:interest:save")
    public R save(@RequestBody InterestEntity interest){
			interestService.save(interest);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:interest:update")
    public R update(@RequestBody InterestEntity interest){
			interestService.update(interest);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:interest:delete")
    public R delete(@RequestBody Integer[] interIds){
			interestService.deleteBatch(interIds);

        return R.ok();
    }
	
}
