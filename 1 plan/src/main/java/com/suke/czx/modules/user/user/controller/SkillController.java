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
import com.suke.czx.modules.sys.entity.SysRoleEntity;
import com.suke.czx.modules.user.user.entity.SkillEntity;
import com.suke.czx.modules.user.user.service.SkillService;
import com.suke.czx.common.utils.Constant;
import com.suke.czx.common.utils.PageUtils;
import com.suke.czx.common.utils.Query;
import com.suke.czx.common.utils.R;




/**
 * 技能
 * 
 */
@RestController
@RequestMapping("/user/skill")
public class SkillController {
    @Autowired
    private SkillService skillService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequestMapping("/list")
    @RequiresPermissions("user:skill:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
    	System.out.println(params);
        Query query = new Query(params);

        List<SkillEntity> skillList = skillService.queryList(query);
        int total = skillService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(skillList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

	/**
	 * 角色列表
	 */
    @CrossOrigin(origins = {"http://localhost:8090", "null"})
    @AuthIgnore
    @GetMapping("listAll")
	public R listAll(){
		Map<String, Object> map = new HashMap<>();
		
		List<SkillEntity> list = skillService.queryList(map);
		
		return R.ok().put("list", list);
	}
	/**
	 * 角色列表
	 */
	@RequestMapping("/select")
	@RequiresPermissions("user:skill:list")
	public R select(){
		Map<String, Object> map = new HashMap<>();
		
		List<SkillEntity> list = skillService.queryList(map);
		
		return R.ok().put("list", list);
	}
    /**
     * 信息
     */
    @RequestMapping("/info/{skillId}")
    @RequiresPermissions("user:skill:info")
    public R info(@PathVariable("skillId") Integer skillId){
			SkillEntity skill = skillService.queryObject(skillId);

        return R.ok().put("skill", skill);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:skill:save")
    public R save(@RequestBody SkillEntity skill){
			skillService.save(skill);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:skill:update")
    public R update(@RequestBody SkillEntity skill){
			skillService.update(skill);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:skill:delete")
    public R delete(@RequestBody Integer[] skillIds){
			skillService.deleteBatch(skillIds);

        return R.ok();
    }
	
}
