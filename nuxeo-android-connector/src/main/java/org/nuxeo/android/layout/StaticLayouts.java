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

package org.nuxeo.android.layout;

public class StaticLayouts {

	public final static String DEFAULT_LAYOUT = "{ " + LayoutJSONParser.WIDGETS_DEF + "= ["
		+ "{name : 'title', type : 'text', fields : [ {fieldName : 'dc:title'}, ], labels : {'any': 'Title'} },"
		+ "{name : 'description', type : 'text', fields:[ {'fieldName':'dc:description'}], labels: {'any': 'Description'}},"
		+ "{name : 'valid', type : 'date', fields:[ {'fieldName':'dc:valid'}], labels: {'any':'Validity'}},"
		+ "{name : 'nature', type : 'selectOne', fields:[ {'fieldName':'dc:nature'}], labels:{'any':'Nature'} , "
		+ " selectOptions : "
		+ "[ { itemValue : 'accomodationApplicationForm' , itemLabel : 'Accomodation application form'},"
		+ " { itemValue : 'acknowledgement' , itemLabel : 'Acknowledgement of receipt'},"
		+ " { itemValue : 'application' , itemLabel : 'Application'},"
		+ " { itemValue : 'article' , itemLabel : 'Article'},"
		+ " { itemValue : 'assessment' , itemLabel : 'Assessment'},"
		+ " { itemValue : 'bankAccountDetails' , itemLabel : 'Bank account details'},"
		+ " { itemValue : 'booklet' , itemLabel : 'Booklet'},"
		+ " { itemValue : 'card' , itemLabel : 'Card'},"
		+ " { itemValue : 'CDRom' , itemLabel : 'CD Rom'},"
		+ " { itemValue : 'certificate' , itemLabel : 'Certificate'},"
		+ " { itemValue : 'contract' , itemLabel : 'Contract'},"
		+ " { itemValue : 'convocation' , itemLabel : 'Convocation'},"
		+ " { itemValue : 'credit' , itemLabel : 'Credit'},"
		+ " { itemValue : 'decree' , itemLabel : 'Decree'},"
		+ " { itemValue : 'deliveryNote' , itemLabel : 'Delivery note'},"
		+ " { itemValue : 'email' , itemLabel : 'Email'},"
		+ " { itemValue : 'endorsement' , itemLabel : 'Endorsement'},"
		+ " { itemValue : 'fax' , itemLabel : 'Fax'},"
		+ " { itemValue : 'internshipReport' , itemLabel : 'Internship report'},"
		+ " { itemValue : 'invoice' , itemLabel : 'Invoice'},"
		+ " { itemValue : 'leaveApplication' , itemLabel : 'Leave application'},"
		+ " { itemValue : 'letter' , itemLabel : 'Letter'},"
		+ " { itemValue : 'loanApplication' , itemLabel : 'Loan application'},"
		+ " { itemValue : 'medicalCertificate' , itemLabel : 'Medical certificate'},"
		+ " { itemValue : 'memo' , itemLabel : 'Memo'},"
		+ " { itemValue : 'note' , itemLabel : 'Note'},"
		+ " { itemValue : 'notice' , itemLabel : 'Notice'},"
		+ " { itemValue : 'notification' , itemLabel : 'Notification'},"
		+ " { itemValue : 'order' , itemLabel : 'Order'},"
		+ " { itemValue : 'pressReview' , itemLabel : 'Press review'},"
		+ " { itemValue : 'procedure' , itemLabel : 'Procedure'},"
		+ " { itemValue : 'purchaseOrder' , itemLabel : 'Purchase order'},"
		+ " { itemValue : 'quotation' , itemLabel : 'Quotation'},"
		+ " { itemValue : 'report' , itemLabel : 'Report'},"
		+ " { itemValue : 'serviceCertificate' , itemLabel : 'Service certificate'},"
		+ " { itemValue : 'trainingApplication' , itemLabel : 'Training application'},"
		+ " { itemValue : 'worksheet' , itemLabel : 'Worksheet'} ]"
		+ " },"
		+ "{name='file', type='blob', fields:[ {'fieldName':'file:content'}], labels:{'any':'File'} }"
		+ "], "
		+ " " + LayoutJSONParser.ROWS_DEF + "= ["
		+ "{'widgets':['title']},"
		+ "{'widgets':['description']},"
		+ "{'widgets':['valid']},"
		+ "{'widgets':['nature']},"
		+ "{'widgets':['file']}"
		+ "]}";


	public final static String TEST_LAYOUT = "[ { 'name': 'heading', 'templates': {'any': '/layouts/layout_default_template.xhtml'}, 'rows': [ {'widgets': ['title']}, {'widgets': ['description']} ], 'widgets': [ { 'name': 'title', 'type': 'text', 'labels': {'any': 'Title'}, 'translated': true, 'fields': [ { 'fieldName': 'dc:title', 'propertyName': 'dc:title' }], 'propertiesByWidgetMode': {'edit': { 'required': 'true', 'styleClass': 'dataInputText' }} }, { 'name': 'description', 'type': 'textarea', 'labels': {'any': 'Description'}, 'translated': true, 'fields': [ { 'fieldName': 'dc:description', 'propertyName': 'dc:description' }], 'propertiesByWidgetMode': {'edit': {'styleClass': 'dataInputText'}} } ] }, { 'name': 'file', 'templates': {'any': '/layouts/layout_default_template.xhtml'}, 'rows': [{'widgets': ['file']}], 'widgets': [ { 'name': 'file', 'type': 'template', 'labels': {'any': 'Content'}, 'translated': true, 'widgetModes': { 'edit': 'edit', 'full_edit': 'edit', 'view': 'preview' }, 'fields': [ { 'fieldName': 'file:content', 'propertyName': 'file:content' }, { 'fieldName': 'file:filename', 'propertyName': 'file:filename' } ], 'properties': {'any': {'template': '/widgets/extended_file_widget.xhtml'}} }] }, { 'name': 'dublincore', 'templates': {'any': '/layouts/layout_default_template.xhtml'}, 'rows': [ {'widgets': ['nature']}, {'widgets': ['subjects']}, {'widgets': ['rights']}, {'widgets': ['source']}, {'widgets': ['coverage']}, {'widgets': ['created']}, {'widgets': ['modified']}, {'widgets': ['format']}, {'widgets': ['language']}, {'widgets': ['expired']}, {'widgets': ['author']}, {'widgets': ['contributors']}, {'widgets': ['lastContributor']} ], 'widgets': [ { 'name': 'nature', 'type': 'selectOneDirectory', 'labels': {'any': 'Nature'}, 'translated': true, 'fields': [ { 'fieldName': 'dc:nature', 'propertyName': 'dc:nature' }], 'properties': {'any': { 'directoryName': 'nature', 'localize': 'true', 'ordering': 'ordering,label' }}, 'selectOptions': [ { 'multiple': false, 'itemLabel': 'Article', 'itemValue': 'article' }, { 'multiple': false, 'itemLabel': 'Acknowledgement of receipt', 'itemValue': 'acknowledgement' }, { 'multiple': false, 'itemLabel': 'Certificate', 'itemValue': 'certificate' }, { 'multiple': false, 'itemLabel': 'Endorsement', 'itemValue': 'endorsement' }, { 'multiple': false, 'itemLabel': 'Notice', 'itemValue': 'notice' }, { 'multiple': false, 'itemLabel': 'Credit', 'itemValue': 'credit' }, { 'multiple': false, 'itemLabel': 'Invoice', 'itemValue': 'invoice' }, { 'multiple': false, 'itemLabel': 'Assessment', 'itemValue': 'assessment' }, { 'multiple': false, 'itemLabel': 'Purchase order', 'itemValue': 'purchaseOrder' }, { 'multiple': false, 'itemLabel': 'Delivery note', 'itemValue': 'deliveryNote' }, { 'multiple': false, 'itemLabel': 'Booklet', 'itemValue': 'booklet' }, { 'multiple': false, 'itemLabel': 'Application', 'itemValue': 'application' }, { 'multiple': false, 'itemLabel': 'CD Rom', 'itemValue': 'CDRom' }, { 'multiple': false, 'itemLabel': 'Medical certificate', 'itemValue': 'medicalCertificate' }, { 'multiple': false, 'itemLabel': 'Service certificate', 'itemValue': 'serviceCertificate' }, { 'multiple': false, 'itemLabel': 'Order', 'itemValue': 'order' }, { 'multiple': false, 'itemLabel': 'Contract', 'itemValue': 'contract' }, { 'multiple': false, 'itemLabel': 'Email', 'itemValue': 'email' }, { 'multiple': false, 'itemLabel': 'Convocation', 'itemValue': 'convocation' }, { 'multiple': false, 'itemLabel': 'Decree', 'itemValue': 'decree' }, { 'multiple': false, 'itemLabel': 'Leave application', 'itemValue': 'leaveApplication' }, { 'multiple': false, 'itemLabel': 'Training application', 'itemValue': 'trainingApplication' }, { 'multiple': false, 'itemLabel': 'Accomodation application form', 'itemValue': 'accomodationApplicationForm' }, { 'multiple': false, 'itemLabel': 'Loan application', 'itemValue': 'loanApplication' }, { 'multiple': false, 'itemLabel': 'Quotation', 'itemValue': 'quotation' }, { 'multiple': false, 'itemLabel': 'Fax', 'itemValue': 'fax' }, { 'multiple': false, 'itemLabel': 'Card', 'itemValue': 'card' }, { 'multiple': false, 'itemLabel': 'Worksheet', 'itemValue': 'worksheet' }, { 'multiple': false, 'itemLabel': 'Letter', 'itemValue': 'letter' }, { 'multiple': false, 'itemLabel': 'Memo', 'itemValue': 'memo' }, { 'multiple': false, 'itemLabel': 'Note', 'itemValue': 'note' }, { 'multiple': false, 'itemLabel': 'Notification', 'itemValue': 'notification' }, { 'multiple': false, 'itemLabel': 'Procedure', 'itemValue': 'procedure' }, { 'multiple': false, 'itemLabel': 'Report', 'itemValue': 'report' }, { 'multiple': false, 'itemLabel': 'Internship report', 'itemValue': 'internshipReport' }, { 'multiple': false, 'itemLabel': 'Bank account details', 'itemValue': 'bankAccountDetails' }, { 'multiple': false, 'itemLabel': 'Press review', 'itemValue': 'pressReview' } ] }, { 'name': 'subjects', 'type': 'template', 'labels': {'any': 'Subjects'}, 'helpLabels': {'edit': 'label.dublincore.subjects.info'}, 'translated': true, 'fields': [ { 'fieldName': 'dc:subjects', 'propertyName': 'dc:subjects' }], 'properties': {'any': {'template': '/widgets/subjects_widget.xhtml'}} }, { 'name': 'rights', 'type': 'text', 'labels': {'any': 'Rights'}, 'translated': true, 'fields': [ { 'fieldName': 'dc:rights', 'propertyName': 'dc:rights' }], 'propertiesByWidgetMode': {'edit': {'styleClass': 'dataInputText'}} }, { 'name': 'source', 'type': 'text', 'labels': {'any': 'Source'}, 'translated': true, 'fields': [ { 'fieldName': 'dc:source', 'propertyName': 'dc:source' }], 'propertiesByWidgetMode': {'edit': {'styleClass': 'dataInputText'}} }, { 'name': 'coverage', 'type': 'template', 'labels': {'any': 'Coverage'}, 'helpLabels': {'edit': 'label.dublincore.coverage.info'}, 'translated': true, 'fields': [ { 'fieldName': 'dc:coverage', 'propertyName': 'dc:coverage' }], 'properties': {'any': {'template': '/widgets/coverage_widget.xhtml'}} }, { 'name': 'created', 'type': 'datetime', 'labels': {'any': 'Created at'}, 'translated': true, 'widgetModes': { 'any': 'view', 'pdf': 'pdf' }, 'fields': [ { 'fieldName': 'dc:created', 'propertyName': 'dc:created' }], 'propertiesByWidgetMode': {'any': {'pattern': '#{nxu:basicDateAndTimeFormater()}'}} }, { 'name': 'modified', 'type': 'datetime', 'labels': {'any': 'Last modified at'}, 'translated': true, 'widgetModes': { 'any': 'view', 'pdf': 'pdf' }, 'fields': [ { 'fieldName': 'dc:modified', 'propertyName': 'dc:modified' }], 'propertiesByWidgetMode': {'any': {'pattern': '#{nxu:basicDateAndTimeFormater()}'}} }, { 'name': 'format', 'type': 'text', 'labels': {'any': 'Format'}, 'translated': true, 'fields': [ { 'fieldName': 'dc:format', 'propertyName': 'dc:format' }], 'propertiesByWidgetMode': {'edit': {'styleClass': 'dataInputText'}} }, { 'name': 'language', 'type': 'text', 'labels': {'any': 'Language'}, 'translated': true, 'fields': [ { 'fieldName': 'dc:language', 'propertyName': 'dc:language' }], 'propertiesByWidgetMode': {'edit': {'styleClass': 'dataInputText'}} }, { 'name': 'expired', 'type': 'datetime', 'labels': {'any': 'Expire on'}, 'translated': true, 'fields': [ { 'fieldName': 'dc:expired', 'propertyName': 'dc:expired' }], 'propertiesByWidgetMode': { 'any': {'pattern': '#{nxu:basicDateFormater()}'}, 'edit': {'format': '#{nxu:basicDateFormater()}'} } }, { 'name': 'author', 'type': 'template', 'labels': {'any': 'Author'}, 'helpLabels': {'edit': 'label.dublincore.author.info'}, 'translated': true, 'widgetModes': { 'any': 'view', 'pdf': 'pdf' }, 'fields': [ { 'fieldName': 'dc:contributors/0', 'propertyName': 'dc:contributors/0' }], 'properties': {'any': {'template': '/widgets/single_user_widget_template.xhtml'}} }, { 'name': 'contributors', 'type': 'template', 'labels': {'any': 'Contributors'}, 'helpLabels': {'edit': 'label.dublincore.contributors.info'}, 'translated': true, 'fields': [ { 'fieldName': 'dc:contributors', 'propertyName': 'dc:contributors' }], 'properties': {'any': {'template': '/widgets/contributors_widget.xhtml'}}, 'propertiesByWidgetMode': {'edit': {'styleClass': 'dataInputText'}} }, { 'name': 'lastContributor', 'type': 'template', 'labels': {'any': 'Last contributor'}, 'helpLabels': {'edit': 'label.dublincore.lastContributor.info'}, 'translated': true, 'widgetModes': { 'any': 'view', 'pdf': 'pdf' }, 'fields': [ { 'fieldName': 'dc:lastContributor', 'propertyName': 'dc:lastContributor' }], 'properties': {'any': {'template': '/widgets/single_user_widget_template.xhtml'}} } ] } ]";
}
