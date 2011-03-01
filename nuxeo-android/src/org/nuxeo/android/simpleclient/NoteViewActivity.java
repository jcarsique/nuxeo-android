package org.nuxeo.android.simpleclient;

import org.nuxeo.ecm.automation.client.jaxrs.model.Document;

import android.text.Html;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.smartnsoft.droid4me.app.AppPublics.BroadcastListenerProvider;
import com.smartnsoft.droid4me.app.AppPublics.SendLoadingIntent;
import com.smartnsoft.droid4me.framework.LifeCycle.BusinessObjectUnavailableException;
import com.smartnsoft.droid4me.framework.LifeCycle.BusinessObjectsRetrievalAsynchronousPolicy;

public class NoteViewActivity extends BaseDocumentViewActivity implements
        BusinessObjectsRetrievalAsynchronousPolicy, SendLoadingIntent,
        BroadcastListenerProvider,
        NuxeoAndroidApplication.TitleBarShowHomeFeature,
        NuxeoAndroidApplication.TitleBarRefreshFeature {

;
    private TextView title;
    private TextView content;
    private WebView htmlContent;
    private ScrollView txtContainer;
    private ScrollView htmlContainer;


    @Override
    public void onRetrieveDisplayObjects() {
        setContentView(R.layout.note_view_layout);
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        htmlContent = (WebView) findViewById(R.id.htmlContent);
        txtContainer = (ScrollView) findViewById(R.id.ScrollViewText);
        htmlContainer = (ScrollView) findViewById(R.id.ScrollViewHtml);
        icon = (ImageView) findViewById(R.id.icon);
    }

    @Override
    public void onRetrieveBusinessObjects()
            throws BusinessObjectUnavailableException {
        Document mynote = fetchDocument(false);
        if (mynote.getString("note:note")==null) {
            fetchDocument(true);
        }
        fetchIcon(mynote);

    }


    @Override
    public void onFulfillDisplayObjects() {

        if (document != null) {
            title.setText(document.getTitle());
            boolean useWebView = false; // WebViews are buggy before 2.3
            if (android.os.Build.VERSION.SDK_INT>8) {
                useWebView = true;
            }
            String mt = document.getString("note:mime_type", "text/plain");
            String contentText = document.getString("note:note", "");

            if ("text/html".equals(mt)) {
                if (useWebView) {
                    htmlContent.loadDataWithBaseURL(null, contentText, "text/html", "utf-8",null);
                    htmlContainer.setVisibility(0);
                    txtContainer.setVisibility(4);
                } else {
                    // Walkaround a WebView bug
                    content.setText(Html.fromHtml(contentText));
                    htmlContainer.setVisibility(4);
                    txtContainer.setVisibility(0);
                }
            } else {
                content.setText(contentText);
                htmlContainer.setVisibility(4);
                txtContainer.setVisibility(0);
            }
        }
    }

    @Override
    protected String getSchemas() {
        return "note";
    }

}