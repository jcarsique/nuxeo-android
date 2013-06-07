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
import org.nuxeo.android.adapters.DocumentsListAdapter;
import org.nuxeo.android.automation.NetworkSettingsActivity;
import org.nuxeo.android.automation.ServerSettingsActivity;
import org.nuxeo.android.documentprovider.LazyDocumentsList;
import org.nuxeo.android.documentprovider.LazyUpdatableDocumentsList;
import org.nuxeo.ecm.automation.client.jaxrs.OperationRequest;
import org.nuxeo.ecm.automation.client.jaxrs.model.Document;
import org.nuxeo.ecm.automation.client.jaxrs.model.Documents;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class AppraisalListActivity extends BaseDocumentsListActivity {

    protected static final int MNU_CONFIG = 20;

    protected static final int MNU_NETWORK_CONFIG = 21;

    protected static final int MNU_SERVER_CONFIG = 22;

    protected static final int CTXMNU_VIEW_PICTURES = 10;

    protected static final int CTXMNU_VALIDATE = 20;

    @Override
    protected void displayDocumentList(ListView listView,
            LazyDocumentsList documentsList) {
        AbstractDocumentListAdapter adapter = new DocumentsListAdapter(this,
                documentsList, R.layout.list_item, getMapping(),
                R.layout.list_item_loading);
        listView.setAdapter(adapter);
    }

    protected Map<Integer, String> getMapping() {

        Map<Integer, String> mapping = new HashMap<Integer, String>();
        mapping.put(R.id.icon, "iconUri");
        mapping.put(R.id.title_entry, "dc:title");
        mapping.put(R.id.status_entry, "status");
        mapping.put(R.id.client, "appraisal:customerName");
        mapping.put(R.id.declaration_date, "(date)dc:created");
        mapping.put(R.id.visite_date, "(date)appraisal:date_of_visit");
        return mapping;
    }

    @Override
    protected LazyUpdatableDocumentsList fetchDocumentsList(byte cacheParam)
            throws Exception {
        String user = getNuxeoContext().getSession().getLogin().getUsername();
        Documents docs = getNuxeoContext().getDocumentManager().query(
                "select * from Appraisal where appraisal:assignee=? AND ecm:currentLifeCycleState=? order by dc:modified desc",
                new String[] { user, "assigned" }, null,
                "common,dublincore,appraisal", 0, 10, cacheParam);
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void setupViews() {
        setContentView(R.layout.listview_layout);
        waitingMessage = (TextView) findViewById(R.id.waitingMessage);
        refreshBtn = findViewById(R.id.refreshBtn);
        listView = (ListView) findViewById(R.id.myList);
    }

    protected void populateMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu(Menu.NONE, MNU_CONFIG, 0, "Config");
        subMenu.add(Menu.NONE, MNU_NETWORK_CONFIG, 0, "Network");
        subMenu.add(Menu.NONE, MNU_SERVER_CONFIG, 1, "Settings");
        menu.add(Menu.NONE, MNU_REFRESH, 2, "Refresh");
    }

    protected void populateContextMenu(Document doc, ContextMenu menu) {
        menu.add(Menu.NONE, CTXMNU_VIEW_DOCUMENT, 0, "View Appraisal");
        menu.add(Menu.NONE, CTXMNU_EDIT_DOCUMENT, 1, "Edit Appraisal");
        menu.add(Menu.NONE, CTXMNU_VIEW_PICTURES, 2, "View pictures");
        menu.add(Menu.NONE, CTXMNU_VALIDATE, 3, "Validate");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int selectedPosition = info.position;
        Document doc = getContextMenuDocument(selectedPosition);

        switch (item.getItemId()) {
        case CTXMNU_VIEW_PICTURES:
            startActivity(new Intent(this, AppraisalContentListActivity.class).putExtra(
                    AppraisalContentListActivity.ROOT_DOC_PARAM, doc));
            return true;
        case CTXMNU_VALIDATE:
            validateDocument(doc);
            return true;
        default:
            return super.onContextItemSelected(item);
        }
    }

    protected void validateDocument(Document doc) {
        OperationRequest request = getNuxeoSession().newRequest(
                "Document.SetLifeCycle");
        request.setInput(doc);
        request.set("value", "to_expert_visit_done");
        documentsList.updateDocument(doc, request);
    }

    @Override
    protected void onListItemClicked(int listItemPosition) {
        Document doc = documentsList.getDocument(listItemPosition);
        startActivity(new Intent(this, AppraisalContentListActivity.class).putExtra(
                AppraisalContentListActivity.ROOT_DOC_PARAM, doc));
    }
    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listactivity_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle all of the possible menu actions.
		switch (item.getItemId()) {

		case R.id.itemNetworkConfig:
            startActivity(new Intent(getApplicationContext(),
                    NetworkSettingsActivity.class));
			break;
		case R.id.itemServerSettings:
            startActivity(new Intent(getApplicationContext(),
                    ServerSettingsActivity.class));
			break;
		case R.id.itemRefresh:
			doRefresh();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
