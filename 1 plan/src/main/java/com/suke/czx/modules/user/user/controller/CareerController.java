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
import com.suke.czx.modules.user.user.service.CareerService;
import com.suke.czx.common.utils.PageUtils;
import com.suke.czx.common.utils.Query;
import com.suke.czx.common.utils.R;




/**
 * career
 * 
 * @author czx
 * @email object_czx@163.com
 * @date 2019-04-30 22:12:39
 */
@RestController
@RequestMapping("/user/career")
public class CareerController {
    @Autowired
    private CareerService careerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:career:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        List<CareerEntity> careerList = careerService.queryList(query);
        int total = careerService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(careerList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

	/**
	 * 职位列表
	 */
    @CrossOrigin(origins = {"http://localhost:8090", "null"})
    @AuthIgnore
    @GetMapping("listAll")
	public R listAll(){
		Map<String, Object> map = new HashMap<>();
		
		List<CareerEntity> list = careerService.queryList(map);
		
		return R.ok().put("list", list);
	}
    /**
     * 信息
     */
    @RequestMapping("/info/{careerId}")
    @RequiresPermissions("user:career:info")
    public R info(@PathVariable("careerId") Integer careerId){
			CareerEntity career = careerService.queryObject(careerId);

        return R.ok().put("career", career);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:career:save")
    public R save(@RequestBody CareerEntity career){
			careerService.save(career);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:career:update")
    public R update(@RequestBody CareerEntity career){
			careerService.update(career);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:career:delete")
    public R delete(@RequestBody Integer[] careerIds){
			careerService.deleteBatch(careerIds);

        return R.ok();
    }
	
}
