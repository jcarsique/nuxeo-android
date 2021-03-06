/*
 * (C) Copyright 2011 Nuxeo SAS (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Nuxeo - initial API and implementation
 */
package org.nuxeo.android.simpleclient.listing;

import org.nuxeo.android.simpleclient.docviews.BaseDocumentViewActivity;
import org.nuxeo.android.simpleclient.service.NuxeoAndroidServices;
import org.nuxeo.ecm.automation.client.jaxrs.model.Document;
import org.nuxeo.ecm.automation.client.jaxrs.model.Documents;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.smartnsoft.droid4me.framework.DetailsProvider.ObjectEvent;
import com.smartnsoft.droid4me.framework.LifeCycle.BusinessObjectUnavailableException;

public class DocumentChildrenListActivity extends BaseDocumentListActivity {

    @Override
    protected Documents getDocuments(boolean refresh)
            throws BusinessObjectUnavailableException {
        String uuid = getIntent().getStringExtra(
                BaseDocumentViewActivity.DOCUMENT_ID);
        return NuxeoAndroidServices.getInstance().getChildren(uuid, refresh);
    }

    @Override
    public Intent handleDocumentEventOnListItem(Activity activity,
            Object viewAttributes, View view, Document doc,
            ObjectEvent objectEvent) {
        if (objectEvent == ObjectEvent.Clicked) {
            if (DocumentHelper.isFolderish(doc)) {
                return new Intent(activity, DocumentChildrenListActivity.class).putExtra(
                        BaseDocumentViewActivity.DOCUMENT_ID, doc.getId()).putExtra(
                        BaseDocumentViewActivity.DOCUMENT, doc);
            } else {
                return super.handleDocumentEventOnListItem(activity,
                        viewAttributes, view, doc, objectEvent);
            }
        }
        return null;
    }

}
