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

package net.sareweb.barazkide.service.impl;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;

import net.sareweb.barazkide.model.Garden;
import net.sareweb.barazkide.service.GardenLocalServiceUtil;
import net.sareweb.barazkide.service.MembershipServiceUtil;
import net.sareweb.barazkide.service.base.GardenServiceBaseImpl;

/**
 * The implementation of the garden remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link net.sareweb.barazkide.service.GardenService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author A.Galdos
 * @see net.sareweb.barazkide.service.base.GardenServiceBaseImpl
 * @see net.sareweb.barazkide.service.GardenServiceUtil
 */
public class GardenServiceImpl extends GardenServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link net.sareweb.barazkide.service.GardenServiceUtil} to access the garden remote service.
	 */
	
	public Garden addGarden(String name, String comment, double lat, double lng, long gardenImageId) throws SystemException, PrincipalException{
		Garden garden = GardenLocalServiceUtil.createGarden(CounterLocalServiceUtil.increment());
		
		garden.setOwnerUserId(getGuestOrUserId());
		garden.setCreateDate(new Date());
		garden.setModifiedDate(new Date());
		garden.setName(decode(name));
		garden.setComment(decode(comment));
		garden.setLat(lat);
		garden.setLng(lng);
		garden.setGardenImageId(gardenImageId);
		
		try {
			DLFolder folder = DLFolderLocalServiceUtil.addFolder(getGuestOrUserId(), 120849l, 120849l, false, 120874l, garden.getName(), garden.getName(), new ServiceContext());
			garden.setGardenFolderId(folder.getFolderId());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		garden = GardenLocalServiceUtil.addGarden(garden);
		
		MembershipServiceUtil.addMembership(garden.getOwnerUserId(), garden.getGardenId());
		
		return garden;
	}
	
	public Garden updateGardenImage(long gardenId, String imageTitle) throws PortalException, SystemException{
		Garden garden = GardenLocalServiceUtil.getGarden(gardenId);
		garden.setImageTitle(imageTitle);
		return GardenLocalServiceUtil.updateGarden(garden);
	}
	
	public List<Garden> getGardens() throws SystemException{
		return GardenLocalServiceUtil.getGardens(-1, -1);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Garden> getUserGardensFromDate(long ownerUserId, long date, boolean ascending, int blockSize) throws SystemException{
		DynamicQuery dq = DynamicQueryFactoryUtil.forClass(Garden.class);
		
		//0 date means now
		Date dateTmp = new Date(date);
		if(date==0)dateTmp = new Date();
		
		if(ownerUserId!=0){
			dq.add(PropertyFactoryUtil.forName("ownerUserId").eq(ownerUserId));
		}
		
		Criterion dateCr = null;
		Order order = null;
		if(ascending){
			dateCr = PropertyFactoryUtil.forName("createDate").ge(dateTmp);
			order = OrderFactoryUtil.asc("createDate");
		}
		else{
			dateCr = PropertyFactoryUtil.forName("createDate").le(dateTmp);
			order = OrderFactoryUtil.desc("createDate");
		}
		dq.add(dateCr);
		dq.addOrder(order);
		if(blockSize>0)
			return GardenLocalServiceUtil.dynamicQuery(dq,0,blockSize);
		else
			return GardenLocalServiceUtil.dynamicQuery(dq);
	}
	
	public List<Garden> getFollowedGardensOlderThanDate(long userId, long followingDate, int blockSize)
			throws SystemException{
		return gardenFinder.findFollowedGardensOlderThanDate(userId, followingDate, blockSize);
	}
	
	public List<Garden> getFollowedGardensNewerThanDate(long userId, long followingDate, int blockSize)
			throws SystemException{
		return gardenFinder.findFollowedGardensNewerThanDate(userId, followingDate, blockSize);
	}
	
	public List<Garden> getParticipatingGardensOlderThanDate(long userId, long participatingDate, int blockSize)
			throws SystemException{
		return gardenFinder.findParticipatingGardensOlderThanDate(userId, participatingDate, blockSize);
	}
	
	public List<Garden> getParticipatingGardensNewerThanDate(long userId, long participatingDate, int blockSize)
			throws SystemException{
		return gardenFinder.findParticipatingGardensNewerThanDate(userId, participatingDate, blockSize);
	}
	
	private String decode(String codedString){
		try {
			return URLDecoder.decode(codedString, "UTF-8");
		} catch (Exception e) {
			return "ERROR";
		}
	}
	
	
}