package com.zjw.notice.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zjw.domain.Notice;
import com.zjw.notice.service.NoticeService;
import com.zjw.common.dao.BaseDAO;
import com.zjw.common.util.PageBean;

/***
 * 
 * @Description: ���¹���ҵ���ʵ����
 * 
 * @author zjw
 * 
 * @create time 2015-6-13 ����09:55:51
 */

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

	@Resource(name = "baseDAO")
	private BaseDAO<Notice> baseDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Notice> findNoticeList(Notice s_notice, PageBean pageBean) {
		List<Object> params = new ArrayList<Object>(); // ��װ����
		StringBuffer sql = new StringBuffer("from Notice");
		// ��̨��ǩ�����еĲ�ѯ
		if (s_notice != null) {
			if (StringUtils.hasText(s_notice.getTitle())) {
				sql.append(" and title like ?");
				params.add("%" + s_notice.getTitle() + "%");
			}
		}
		sql.append(" order by createTime desc");
		if (pageBean != null) {
			return baseDAO.find(sql.toString().replaceFirst("and", "where"),
					params, pageBean);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Notice getNoticeById(int noticeId) {
		return baseDAO.get(Notice.class, noticeId);
	}

}
