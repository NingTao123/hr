package com.huarui.hr.lsy.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huarui.hr.entity.SysRight;

public class MyUtil {
	/**
	 * ���ݸ�id�õ���ص�Ȩ��
	 * 
	 */
	public static List<SysRight> getRightByParentId(List<SysRight> list, Integer parentId) {
		List<SysRight> meun = new ArrayList<SysRight>();
		for (SysRight r : list) {
			if (r.getRight_parent_code() == parentId) {
				meun.add(r);
			}
		}
		return meun;
	}

	/**
	 * �˵��Ҷ��ӵķ���
	 */
	public static void getSon(List<SysRight> list, Map<String, Object> fatherMap, Integer fatherId) {
		// ��Ŷ��ӵļ���
		List<Map<String, Object>> sons = new ArrayList<Map<String, Object>>();
		for (SysRight sr : list) {
			// �ж��ǲ���ָ���Ķ���
			if (sr.getRight_parent_code() == fatherId) {
				Map<String, Object> son = new HashMap<String, Object>();
				son.put("id", sr.getRight_code());
				son.put("text", sr.getRight_text());
				// �˶��ӽڵ㻹�Ǳ��˵ĸ���,sr.getRight_parent_code()!=0 ��ʾ���������ڵ�
				if ("parent".equals(sr.getRight_type()) && sr.getRight_parent_code() != 0) {
					son.put("state", "open");
					// �ҵ�ǰ����Ķ���
					getSon(list, son, sr.getRight_code());
				}
				// �Ѷ��Ӵ�ŵ����Ӽ���
				sons.add(son);
			}
		}
		// �Ѷ��Ӱ󶨵�����
		fatherMap.put("children", sons);
	}
}
