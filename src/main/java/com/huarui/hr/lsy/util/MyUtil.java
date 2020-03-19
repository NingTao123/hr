package com.huarui.hr.lsy.util;

import java.util.ArrayList;
import java.util.List;

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
}
