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
package org.nuxeo.android.appraisal;

import java.util.HashMap;
import java.util.Map;

import org.nuxeo.android.activities.BaseDocumentLayoutActivity;
import org.nuxeo.android.activities.BaseDocumentsListActivity;
import org.nuxeo.android.adapters.AbstractDocumentListAdapter;
import org.nuxeo.android.adapters.DocumentAttributeResolver;
import org.nuxeo.android.adapters.DocumentsListAdapter;
import org.nuxeo.android.documentprovider.LazyDocumentsList;
import org.nuxeo.android.documentprovider.LazyUpdatableDocumentsList;
import org.nuxeo.ecm.automation.client.jaxrs.OperationRequest;
import org.nuxeo.ecm.automation.client.jaxrs.model.Document;
import org.nuxeo.ecm.automation.client.jaxrs.model.Documents;
import org.nuxeo.ecm.automation.client.jaxrs.model.PathRef;
import org.nuxeo.ecm.automation.client.jaxrs.model.PropertiesHelper;
import org.nuxeo.ecm.automation.client.jaxrs.model.PropertyMap;

import android.widget.ListView;
import android.widget.TextView;

public class AppraisalContentListActivity extends BaseDocumentsListActivity {

    public static final String ROOT_DOC_PARAM = "rootDoc";

    protected Map<Integer, String> getMapping() {
        Map<Integer, String> mapping = new HashMap<Integer, String>();
        mapping.put(R.id.title_entry, "dc:title");
        mapping.put(R.id.description, "dc:description");
        mapping.put(R.id.status_entry, "status");
        mapping.put(R.id.thumb, DocumentAttributeResolver.PICTUREURI
                + ":Medium");
        return mapping;
    }

    @Override
    protected void displayDocumentList(ListView listView,
            LazyDocumentsList documentsList) {
        AbstractDocumentListAdapter adapter = new DocumentsListAdapter(this,
                documentsList, R.layout.picture_item, getMapping(),
                R.layout.list_item_loading);
        listView.setAdapter(adapter);
    }

    @Override
    protected LazyUpdatableDocumentsList fetchDocumentsList(byte cacheParam)
            throws Exception {
    	Document doc = getInitParam(ROOT_DOC_PARAM, Document.class);
    	setTitle(doc.getName() + " pictures");
        Documents docs = getNuxeoContext().getDocumentManager().query(
                "select * from Document where ecm:mixinType != \"HiddenInNavigation\" AND ecm:currentLifeCycleState!='deleted' AND ecm:isCheckedInVersion = 0 AND ecm:parentId=? order by dc:modified desc",
                new String[] { doc.getId() },
                null, null, 0, 10, cacheParam);
        if (docs != null) {
            return docs.asUpdatableDocumentsList();
        }
        throw new RuntimeException("fetch Operation did return null");
    }

    @Override
    protected Class<? extends BaseDocumentLayoutActivity> getEditActivityClass() {
        return AppraisalLayoutActivity.class;
    }

    @Override
    protected Document initNewDocument(String type) {
        if (documentsList == null) {
            return null;
        } else {
            return new Document(
                    getInitParam(ROOT_DOC_PARAM, Document.class).getPath(),
                    "appraisalPicture-" + documentsList.getCurrentSize(),
                    "File");
        }
    }

    @Override
    protected void onDocumentCreate(Document newDocument) {
        OperationRequest createOperation = getNuxeoSession().newRequest(
                "Picture.Create");

        PropertyMap dirty = newDocument.getDirtyProperties();
        if (dirty.get("file:content") != null) {
            dirty.map().put("originalPicture", dirty.get("file:content"));
            dirty.map().remove("file:content");
        }
        String dirtyString = PropertiesHelper.toStringProperties(dirty);

        PathRef parent = new PathRef(newDocument.getParentPath());
        createOperation.setInput(parent).set("properties", dirtyString);
        if (newDocument.getName() != null) {
            createOperation.set("name", newDocument.getName());
        }
        documentsList.createDocument(newDocument, createOperation);
    }

    @Override
    protected void setupViews() {
        setContentView(R.layout.listview_layout);
        waitingMessage = (TextView) findViewById(R.id.waitingMessage);
        refreshBtn = findViewById(R.id.refreshBtn);
        listView = (ListView) findViewById(R.id.myList);
        registerDocTypesForCreation("Picture", "Picture");
    }

    
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.contentlist_menu, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle all of the possible menu actions.
//		switch (item.getItemId()) {
//
//		case R.id.item_new_item:
//			if (getDocTypesForCreation().size() == 1) {
//				forceRefresh();
//				Document newDoc = initNewDocument(getDocTypesForCreation()
//						.keySet().iterator().next());
//				if (newDoc != null) {
//					startActivityForResult(
//							new Intent(this, getEditActivityClass())
//									.putExtra(
//											BaseDocumentLayoutActivity.DOCUMENT,
//											newDoc).putExtra(
//											BaseDocumentLayoutActivity.MODE,
//											LayoutMode.CREATE),
//							ACTION_CREATE_DOCUMENT);
//				}
//			}
//			break;
//		case R.id.itemRefresh:
//			doRefresh();
//			break;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
