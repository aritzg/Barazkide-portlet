/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package net.sareweb.barazkide.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import net.sareweb.barazkide.model.GardenImage;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing GardenImage in entity cache.
 *
 * @author A.Galdos
 * @see GardenImage
 * @generated
 */
public class GardenImageCacheModel implements CacheModel<GardenImage>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{gardenImageId=");
		sb.append(gardenImageId);
		sb.append(", gardenId=");
		sb.append(gardenId);
		sb.append(", imgId=");
		sb.append(imgId);
		sb.append(", imgName=");
		sb.append(imgName);
		sb.append(", imgUrl=");
		sb.append(imgUrl);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append("}");

		return sb.toString();
	}

	public GardenImage toEntityModel() {
		GardenImageImpl gardenImageImpl = new GardenImageImpl();

		gardenImageImpl.setGardenImageId(gardenImageId);
		gardenImageImpl.setGardenId(gardenId);
		gardenImageImpl.setImgId(imgId);

		if (imgName == null) {
			gardenImageImpl.setImgName(StringPool.BLANK);
		}
		else {
			gardenImageImpl.setImgName(imgName);
		}

		if (imgUrl == null) {
			gardenImageImpl.setImgUrl(StringPool.BLANK);
		}
		else {
			gardenImageImpl.setImgUrl(imgUrl);
		}

		if (createDate == Long.MIN_VALUE) {
			gardenImageImpl.setCreateDate(null);
		}
		else {
			gardenImageImpl.setCreateDate(new Date(createDate));
		}

		gardenImageImpl.resetOriginalValues();

		return gardenImageImpl;
	}

	public long gardenImageId;
	public long gardenId;
	public long imgId;
	public String imgName;
	public String imgUrl;
	public long createDate;
}