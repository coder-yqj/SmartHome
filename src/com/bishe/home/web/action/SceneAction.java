package com.bishe.home.web.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.bishe.home.entity.QueryResult;
import com.bishe.home.entity.Scene;
import com.bishe.home.service.SceneService;
import com.bishe.home.util.JsonUtil;
import com.bishe.home.util.ReturnUtil;

@Controller
public class SceneAction {

	private QueryResult<Scene> queryResult;

	private Scene scene = new Scene();// 场景

	@Resource(name = "sceneService")
	private SceneService sceneService;
	static Logger logger = Logger.getLogger(SceneAction.class);

	// 查询所有的场景
	public void findAll() {
		
		QueryResult<Scene> queryResult = sceneService.findAll();
		// System.out.println(queryResult.getRows());
		try {
			ReturnUtil.returnJsonStr(JsonUtil.getJsonStr(1, queryResult.getRows()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("findAll()...返回出错");
		}
	}

	// 到场景页面
	public String toShowScene() {
		queryResult = sceneService.findAll();
		return "toPage";
	}

	// 添加场景
	public void add() {
		sceneService.save(scene);
		try {
			ReturnUtil.returnJsonStr(JsonUtil.getJsonStr(1, "添加成功"));
		} catch (Exception e) {
			logger.error("添加场景返回错误");
			e.printStackTrace();
		}
	}
	//更新场景
	public void update(){
		sceneService.update(scene);
		try {
			ReturnUtil.returnJsonStr(JsonUtil.getJsonStr(1, "更新成功"));
		} catch (Exception e) {
			logger.error("更新场景返回错误");
			e.printStackTrace();
		}
	}
	
	//删除场景
	public void delete(){
		sceneService.delete(scene.getId());
		try {
			ReturnUtil.returnJsonStr(JsonUtil.getJsonStr(1, "删除成功"));
		} catch (Exception e) {
			logger.error("删除场景返回错误");
			e.printStackTrace();
		}
	}
	
	public QueryResult<Scene> getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(QueryResult<Scene> queryResult) {
		this.queryResult = queryResult;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

}
