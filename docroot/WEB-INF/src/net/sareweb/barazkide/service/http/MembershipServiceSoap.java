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

import net.sareweb.barazkide.service.MembershipServiceUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link net.sareweb.barazkide.service.MembershipServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link net.sareweb.barazkide.model.MembershipSoap}.
 * If the method in the service utility returns a
 * {@link net.sareweb.barazkide.model.Membership}, that is translated to a
 * {@link net.sareweb.barazkide.model.MembershipSoap}. Methods that SOAP cannot
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
 * @see       MembershipServiceHttp
 * @see       net.sareweb.barazkide.model.MembershipSoap
 * @see       net.sareweb.barazkide.service.MembershipServiceUtil
 * @generated
 */
public class MembershipServiceSoap {
	public static boolean addMembership(long userId, long gardenId)
		throws RemoteException {
		try {
			boolean returnValue = MembershipServiceUtil.addMembership(userId,
					gardenId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean addMembershipRequest(long userId, long gardenId)
		throws RemoteException {
		try {
			boolean returnValue = MembershipServiceUtil.addMembershipRequest(userId,
					gardenId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean acceptMembershipRequest(long userId, long gardenId)
		throws RemoteException {
		try {
			boolean returnValue = MembershipServiceUtil.acceptMembershipRequest(userId,
					gardenId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean rejectMembershipRequest(long userId, long gardenId)
		throws RemoteException {
		try {
			boolean returnValue = MembershipServiceUtil.rejectMembershipRequest(userId,
					gardenId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean removeMembership(long userId, long gardenId)
		throws RemoteException {
		try {
			boolean returnValue = MembershipServiceUtil.removeMembership(userId,
					gardenId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static net.sareweb.barazkide.model.MembershipSoap[] findMemberUsers(
		long gardenId, int status) throws RemoteException {
		try {
			java.util.List<com.liferay.portal.model.User> returnValue = MembershipServiceUtil.findMemberUsers(gardenId,
					status);

			return null;//net.sareweb.barazkide.model.MembershipSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MembershipServiceSoap.class);
}