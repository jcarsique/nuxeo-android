/*
 * (C) Copyright 2010-2011 Nuxeo SAS (http://nuxeo.com/) and contributors.
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
 *
 */

package org.nuxeo.android.simpleclient.provider;

import android.content.SearchRecentSuggestionsProvider;

/**
 * @author Nuxeo & Smart&Soft
 * @since 2011.02.18
 */
public final class DocumentsSearchProvider extends
        SearchRecentSuggestionsProvider {

    public final static String AUTHORITY = "org.nuxeo.android.simpleclient.provider.PartnersSearchProvider";

    public final static int MODE = SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES;

    public DocumentsSearchProvider() {
        setupSuggestions(DocumentsSearchProvider.AUTHORITY,
                DocumentsSearchProvider.MODE);
    }

}
