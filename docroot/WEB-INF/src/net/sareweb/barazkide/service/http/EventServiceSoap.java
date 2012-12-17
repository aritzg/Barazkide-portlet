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

package net.sareweb.barazkide.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import net.sareweb.barazkide.service.EventServiceUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link net.sareweb.barazkide.service.EventServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link net.sareweb.barazkide.model.EventSoap}.
 * If the method in the service utility returns a
 * {@link net.sareweb.barazkide.model.Event}, that is translated to a
 * {@link net.sareweb.barazkide.model.EventSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/api/secure/axis. Set the property
 * <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    A.Galdos
 * @see       EventServiceHttp
 * @see       net.sareweb.barazkide.model.EventSoap
 * @see       net.sareweb.barazkide.service.EventServiceUtil
 * @generated
 */
public class EventServiceSoap {
	public static net.sareweb.barazkide.model.EventSoap addEvent(
		long gardenId, long creatorUserId, long destinationUserId,
		long gardenImageId, java.lang.String eventType,
		java.lang.String eventText) throws RemoteException {
		try {
			net.sareweb.barazkide.model.Event returnValue = EventServiceUtil.addEvent(gardenId,
					creatorUserId, destinationUserId, gardenImageId, eventType,
					eventText);

			return net.sareweb.barazkide.model.EventSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static net.sareweb.barazkide.model.EventSoap[] findEventsInFollowedGardensOlderThanDate(
		long userId, long followingDate, int blockSize)
		throws RemoteException {
		try {
			java.util.List<net.sareweb.barazkide.model.Event> returnValue = EventServiceUtil.findEventsInFollowedGardensOlderThanDate(userId,
					followingDate, blockSize);

			return net.sareweb.barazkide.model.EventSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static net.sareweb.barazkide.model.EventSoap[] findEventsInFollowedGardensNewerThanDate(
		long userId, long followingDate, int blockSize)
		throws RemoteException {
		try {
			java.util.List<net.sareweb.barazkide.model.Event> returnValue = EventServiceUtil.findEventsInFollowedGardensNewerThanDate(userId,
					followingDate, blockSize);

			return net.sareweb.barazkide.model.EventSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static net.sareweb.barazkide.model.EventSoap[] findEventsInGardenOlderThanDate(
		long gardenId, long eventDate, int blockSize) throws RemoteException {
		try {
			java.util.List<net.sareweb.barazkide.model.Event> returnValue = EventServiceUtil.findEventsInGardenOlderThanDate(gardenId,
					eventDate, blockSize);

			return net.sareweb.barazkide.model.EventSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static net.sareweb.barazkide.model.EventSoap[] findEventsInGardenNewerThanDate(
		long gardenId, long eventDate, int blockSize) throws RemoteException {
		try {
			java.util.List<net.sareweb.barazkide.model.Event> returnValue = EventServiceUtil.findEventsInGardenNewerThanDate(gardenId,
					eventDate, blockSize);

			return net.sareweb.barazkide.model.EventSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(EventServiceSoap.class);
}