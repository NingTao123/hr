package com.huarui.hr.lsy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huarui.hr.entity.SysRight;
import com.huarui.hr.entity.Users;
import com.huarui.hr.lsy.service.SysRightService;
import com.huarui.hr.lsy.util.MyUtil;

@Controller
@RequestMapping("sysRight")
public class SysRightController {
	@Autowired
	private SysRightService srs;
	// ���ݸ��׵Ĵ��ӽڵ�
	@RequestMapping("/querySysRightJson3")
	@ResponseBody // �ѷ��ص�����ת��JSON
	public List querySysRightJson3(HttpSession session,Integer id) {
		System.out.println("sysRight-querySysRightJson3��" + id);
		if(id==null) {
			id=0;
		}
		//�õ���ǰ�û�������Ȩ��
		List<SysRight> userList=((Users)session.getAttribute("user")).getRole().getRights();
		System.out.println("��ǰ�û���Ȩ��:"+userList);
		//���ݸ�id���Ӳ˵�
		List<SysRight> list = MyUtil.getRightByParentId(userList, id);
		List<Map<String, Object>> meunList = new ArrayList<Map<String, Object>>();
		for (SysRight sysRight : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", sysRight.getRight_code());
			map.put("text", sysRight.getRight_text());
			if ("parent".equals(sysRight.getRight_type())) {
				//�Ǹ�����۵�������Ϊ����ҳ���ϵ������ڵ��ʱ��������
				map.put("state", "closed");
			}else {
				//���ӽڵ�,������
				Map<String,Object> attr=new HashMap<String, Object>();
				attr.put("url",sysRight.getRight_url());
				attr.put("tip",sysRight.getRight_tip());
				map.put("attributes",attr);
			}
			meunList.add(map);
		}
		return meunList;
	}
}
