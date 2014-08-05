/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.marcelmika.lims.persistence.generated.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import com.marcelmika.lims.persistence.generated.model.Conversation;
import com.marcelmika.lims.persistence.generated.model.ConversationModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Conversation service. Represents a row in the &quot;lims_Conversation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.marcelmika.lims.persistence.generated.model.ConversationModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ConversationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ConversationImpl
 * @see com.marcelmika.lims.persistence.generated.model.Conversation
 * @see com.marcelmika.lims.persistence.generated.model.ConversationModel
 * @generated
 */
public class ConversationModelImpl extends BaseModelImpl<Conversation>
	implements ConversationModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a conversation model instance should use the {@link com.marcelmika.lims.persistence.generated.model.Conversation} interface instead.
	 */
	public static final String TABLE_NAME = "lims_Conversation";
	public static final Object[][] TABLE_COLUMNS = {
			{ "cid", Types.BIGINT },
			{ "conversationId", Types.VARCHAR },
			{ "conversationType", Types.VARCHAR },
			{ "updatedAt", Types.TIMESTAMP }
		};
	public static final String TABLE_SQL_CREATE = "create table lims_Conversation (cid LONG not null primary key,conversationId VARCHAR(75) null,conversationType VARCHAR(75) null,updatedAt DATE null)";
	public static final String TABLE_SQL_DROP = "drop table lims_Conversation";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.marcelmika.lims.persistence.generated.model.Conversation"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.marcelmika.lims.persistence.generated.model.Conversation"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.marcelmika.lims.persistence.generated.model.Conversation"),
			true);
	public static long CONVERSATIONID_COLUMN_BITMASK = 1L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.marcelmika.lims.persistence.generated.model.Conversation"));

	public ConversationModelImpl() {
	}

	public long getPrimaryKey() {
		return _cid;
	}

	public void setPrimaryKey(long primaryKey) {
		setCid(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_cid);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return Conversation.class;
	}

	public String getModelClassName() {
		return Conversation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("cid", getCid());
		attributes.put("conversationId", getConversationId());
		attributes.put("conversationType", getConversationType());
		attributes.put("updatedAt", getUpdatedAt());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long cid = (Long)attributes.get("cid");

		if (cid != null) {
			setCid(cid);
		}

		String conversationId = (String)attributes.get("conversationId");

		if (conversationId != null) {
			setConversationId(conversationId);
		}

		String conversationType = (String)attributes.get("conversationType");

		if (conversationType != null) {
			setConversationType(conversationType);
		}

		Date updatedAt = (Date)attributes.get("updatedAt");

		if (updatedAt != null) {
			setUpdatedAt(updatedAt);
		}
	}

	public long getCid() {
		return _cid;
	}

	public void setCid(long cid) {
		_cid = cid;
	}

	public String getConversationId() {
		if (_conversationId == null) {
			return StringPool.BLANK;
		}
		else {
			return _conversationId;
		}
	}

	public void setConversationId(String conversationId) {
		_columnBitmask |= CONVERSATIONID_COLUMN_BITMASK;

		if (_originalConversationId == null) {
			_originalConversationId = _conversationId;
		}

		_conversationId = conversationId;
	}

	public String getOriginalConversationId() {
		return GetterUtil.getString(_originalConversationId);
	}

	public String getConversationType() {
		if (_conversationType == null) {
			return StringPool.BLANK;
		}
		else {
			return _conversationType;
		}
	}

	public void setConversationType(String conversationType) {
		_conversationType = conversationType;
	}

	public Date getUpdatedAt() {
		return _updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		_updatedAt = updatedAt;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Conversation.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Conversation toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Conversation)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	public Conversation toUnescapedModel() {
		return (Conversation)this;
	}

	@Override
	public Object clone() {
		ConversationImpl conversationImpl = new ConversationImpl();

		conversationImpl.setCid(getCid());
		conversationImpl.setConversationId(getConversationId());
		conversationImpl.setConversationType(getConversationType());
		conversationImpl.setUpdatedAt(getUpdatedAt());

		conversationImpl.resetOriginalValues();

		return conversationImpl;
	}

	public int compareTo(Conversation conversation) {
		long primaryKey = conversation.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Conversation)) {
			return false;
		}

		Conversation conversation = (Conversation)obj;

		long primaryKey = conversation.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		ConversationModelImpl conversationModelImpl = this;

		conversationModelImpl._originalConversationId = conversationModelImpl._conversationId;

		conversationModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Conversation> toCacheModel() {
		ConversationCacheModel conversationCacheModel = new ConversationCacheModel();

		conversationCacheModel.cid = getCid();

		conversationCacheModel.conversationId = getConversationId();

		String conversationId = conversationCacheModel.conversationId;

		if ((conversationId != null) && (conversationId.length() == 0)) {
			conversationCacheModel.conversationId = null;
		}

		conversationCacheModel.conversationType = getConversationType();

		String conversationType = conversationCacheModel.conversationType;

		if ((conversationType != null) && (conversationType.length() == 0)) {
			conversationCacheModel.conversationType = null;
		}

		Date updatedAt = getUpdatedAt();

		if (updatedAt != null) {
			conversationCacheModel.updatedAt = updatedAt.getTime();
		}
		else {
			conversationCacheModel.updatedAt = Long.MIN_VALUE;
		}

		return conversationCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{cid=");
		sb.append(getCid());
		sb.append(", conversationId=");
		sb.append(getConversationId());
		sb.append(", conversationType=");
		sb.append(getConversationType());
		sb.append(", updatedAt=");
		sb.append(getUpdatedAt());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append(
			"com.marcelmika.lims.persistence.generated.model.Conversation");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>cid</column-name><column-value><![CDATA[");
		sb.append(getCid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>conversationId</column-name><column-value><![CDATA[");
		sb.append(getConversationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>conversationType</column-name><column-value><![CDATA[");
		sb.append(getConversationType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>updatedAt</column-name><column-value><![CDATA[");
		sb.append(getUpdatedAt());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Conversation.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			Conversation.class
		};
	private long _cid;
	private String _conversationId;
	private String _originalConversationId;
	private String _conversationType;
	private Date _updatedAt;
	private long _columnBitmask;
	private Conversation _escapedModel;
}