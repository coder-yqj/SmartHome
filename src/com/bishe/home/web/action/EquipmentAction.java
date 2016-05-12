package com.bishe.home.web.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.bishe.home.domain.Message;
import com.bishe.home.domain.PublishMsg;
import com.bishe.home.entity.Equipment;
import com.bishe.home.entity.QueryResult;
import com.bishe.home.entity.Scene;
import com.bishe.home.entity.User;
import com.bishe.home.service.EquipmentService;
import com.bishe.home.util.GetUserUtil;
import com.bishe.home.util.JsonUtil;
import com.bishe.home.util.ReturnUtil;

@Controller
public class EquipmentAction {

	// private Map<String, Object> dataMap;
	private int page;
	private int rows;
	private JSONObject jsonObj;
	private Equipment equipment;
	private String filePath;
	private File pic;
	private String picFileName;
	private String picContentType;
	private Integer equipmentId;
	private Double value;
	private List<Integer> ids;

	private Integer sceneId;

	private QueryResult<Equipment> equipments;

	private Long channelId;
	private String userId;

	@Resource(name = "equipmentService")
	private EquipmentService equipmentService;

	static Logger logger = Logger.getLogger(EquipmentAction.class);

	public void testPublish() {
		PublishMsg psh = new PublishMsg(channelId, userId);
		Message message = new Message();
		message.setTitle("安居");
		message.setDescription("设备推送测试");
		JSONObject notification = JSONObject.fromObject(message);
		try {
			psh.push(notification);
		} catch (PushClientException e) {
			e.printStackTrace();
		} catch (PushServerException e) {
			e.printStackTrace();
		}
	}

	// 打开设备开关
	public void turnOn() {
		logger.info("turn on the equipment");
		equipmentService.turnOn(equipmentId);
	}

	public void turnOff() {
		logger.info("turn off the equipment");
		equipmentService.turnOff(equipmentId);
	}

	public void changeValue() {
		logger.info("change the equipment value to " + value);
		equipmentService.chengeTo(value, equipmentId);

	}

	// 查询所有设备
	public void findByPage() throws Exception {
		// System.out.println(sceneId);
		logger.info("Enter findByPage() method...");
		QueryResult<Equipment> queryResult = equipmentService.findByPage(page,
				rows, sceneId);
		// this.toBeJson(queryResult.getRows(), (int) queryResult.getTotal());
		ReturnUtil.returnJsonStr(JsonUtil.getJsonStr(1, queryResult,
				new String[] { "scene" }));

		logger.info("findByPage() method end ...");
		// return null;
	}

	// 增加一个设备
	public String add() {
		logger.info("Enter add() method...");
		logger.info("add Equipment：" + equipment);
		equipment.setUserId(GetUserUtil.getUser().getId());
		equipmentService.save(equipment);
		logger.info("add() method end ...");
		return "list";
	}

	// 上传设备图标
	public String uploadFile() throws Exception {
		logger.info("Enter uploadFile() method...");
		// 获取项目部署的路径
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/upload");
		// 生成新的文件名
		String newFileName = UUID.randomUUID().toString() + "."
				+ FilenameUtils.getExtension(picFileName);// 获取文件的后缀名 aa.jpg -->
		FileUtils.copyFile(pic, new File(realPath + File.separator
				+ newFileName));
		ReturnUtil.returnJsonStr(JsonUtil.getJsonStr("path", newFileName));

		logger.info("uploadFile() method end ...");
		return null;
	}

	// 更加id查找设备
	public void findById() throws Exception {
		logger.info("Enter findById method ...select Equipment id is"
				+ equipmentId);
		Equipment equipment = equipmentService.findById(equipmentId);
		// ReturnUtil.returnJsonStr(JsonUtil.getJsonStr("equipment",
		// equipment));
		ReturnUtil.returnJsonStr(JsonUtil.getJsonStr(1, equipment,
				new String[] { "scene" }));
		logger.info("findById() method end ...");

	}

	// 编辑设备
	public void edit() throws Exception {
		logger.info("Enter edit method  Equipment is" + equipment);
		equipmentService.update(equipment);
		logger.info("edit method end ...");
		ReturnUtil.returnJsonStr(JsonUtil.getJsonStr(1, "保存成功"));
	}

	// 更加id数组删除相应的设备
	public void delByIds() {
		logger.info("Enter delByIds method ids are" + ids);
		for (Integer id : ids) {
			equipmentService.deleteById(id);
		}
		logger.info("delByIds method end ...");
		try {
			ReturnUtil.returnJsonStr(JsonUtil.getJsonStr(1, "删除成功"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 返回所有的设备列表
	public void findAll() throws Exception {
		logger.info("Enter method findAll()...");
		QueryResult<Equipment> queryResult = equipmentService.findAll();
		try {
			ReturnUtil.returnJsonStr(JsonUtil.getJsonStr(1,
					queryResult.getRows(), new String[] { "scene" }));
		} catch (Exception e) {
			e.printStackTrace();
			ReturnUtil.returnJsonStr(JsonUtil.getJsonStr(0, "返回所有设备出错"));
			logger.error("返回所有设备出错。");
		}
	}

	public String toShowSceneEq() {
		// System.out.println("sceneId:"+ sceneId);
		equipments = equipmentService.findBySceneId(sceneId);
		// System.out.println(equipments.getRows());
		return "showEquipment";

	}

	// 根据场景ID查找对应的设备
	public void findBySceneId() throws Exception {
		QueryResult<Equipment> queryResult = equipmentService
				.findBySceneId(sceneId);
		try {
			ReturnUtil.returnJsonStr(JsonUtil.getJsonStr(1,
					queryResult.getRows(), new String[] { "scene" }));
		} catch (Exception e) {
			e.printStackTrace();
			ReturnUtil.returnJsonStr(JsonUtil.getJsonStr(0, "返回所有设备出错"));
			logger.error("返回所有设备出错。");
		}
	}

	public void toBeJson(List list, int total) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();

		jsonObj = new JSONObject();// new一个JSON
		jsonObj.accumulate("total", total);// total代表一共有多少数据
		jsonObj.accumulate("rows", list);// row是代表显示的页的数据

		response.setCharacterEncoding("utf-8");// 指定为utf-8
		response.getWriter().write(jsonObj.toString());// 转化为JSOn格式

	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public QueryResult<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(QueryResult<Equipment> equipments) {
		this.equipments = equipments;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	 

}
