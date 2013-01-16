/**
 * 
 * Copyright (c) 2009 Hatha Systems.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Nikolai Mansourov (Hatha Systems) - initial API and implementation
 *     Gabriel Barbier (Mia-Software) - minor evolutions for version 1.1
 */
package org.eclipse.gmt.modisco.omg.kdm.data.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.eclipse.gmt.modisco.omg.kdm.data.util.DataAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DataItemProviderAdapterFactory extends DataAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.DataModel} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataModelItemProvider dataModelItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.DataModel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDataModelAdapter() {
		if (dataModelItemProvider == null) {
			dataModelItemProvider = new DataModelItemProvider(this);
		}

		return dataModelItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.DataResource} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataResourceItemProvider dataResourceItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.DataResource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDataResourceAdapter() {
		if (dataResourceItemProvider == null) {
			dataResourceItemProvider = new DataResourceItemProvider(this);
		}

		return dataResourceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.IndexElement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IndexElementItemProvider indexElementItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.IndexElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIndexElementAdapter() {
		if (indexElementItemProvider == null) {
			indexElementItemProvider = new IndexElementItemProvider(this);
		}

		return indexElementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.UniqueKey} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UniqueKeyItemProvider uniqueKeyItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.UniqueKey}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createUniqueKeyAdapter() {
		if (uniqueKeyItemProvider == null) {
			uniqueKeyItemProvider = new UniqueKeyItemProvider(this);
		}

		return uniqueKeyItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.Index} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IndexItemProvider indexItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.Index}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIndexAdapter() {
		if (indexItemProvider == null) {
			indexItemProvider = new IndexItemProvider(this);
		}

		return indexItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.KeyRelation} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected KeyRelationItemProvider keyRelationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.KeyRelation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createKeyRelationAdapter() {
		if (keyRelationItemProvider == null) {
			keyRelationItemProvider = new KeyRelationItemProvider(this);
		}

		return keyRelationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ReferenceKey} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceKeyItemProvider referenceKeyItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ReferenceKey}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createReferenceKeyAdapter() {
		if (referenceKeyItemProvider == null) {
			referenceKeyItemProvider = new ReferenceKeyItemProvider(this);
		}

		return referenceKeyItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.DataContainer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataContainerItemProvider dataContainerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.DataContainer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDataContainerAdapter() {
		if (dataContainerItemProvider == null) {
			dataContainerItemProvider = new DataContainerItemProvider(this);
		}

		return dataContainerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.Catalog} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CatalogItemProvider catalogItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.Catalog}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCatalogAdapter() {
		if (catalogItemProvider == null) {
			catalogItemProvider = new CatalogItemProvider(this);
		}

		return catalogItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.RelationalSchema} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationalSchemaItemProvider relationalSchemaItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.RelationalSchema}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRelationalSchemaAdapter() {
		if (relationalSchemaItemProvider == null) {
			relationalSchemaItemProvider = new RelationalSchemaItemProvider(this);
		}

		return relationalSchemaItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ColumnSet} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ColumnSetItemProvider columnSetItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ColumnSet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createColumnSetAdapter() {
		if (columnSetItemProvider == null) {
			columnSetItemProvider = new ColumnSetItemProvider(this);
		}

		return columnSetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.RelationalTable} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationalTableItemProvider relationalTableItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.RelationalTable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRelationalTableAdapter() {
		if (relationalTableItemProvider == null) {
			relationalTableItemProvider = new RelationalTableItemProvider(this);
		}

		return relationalTableItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.RelationalView} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationalViewItemProvider relationalViewItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.RelationalView}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRelationalViewAdapter() {
		if (relationalViewItemProvider == null) {
			relationalViewItemProvider = new RelationalViewItemProvider(this);
		}

		return relationalViewItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.RecordFile} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RecordFileItemProvider recordFileItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.RecordFile}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRecordFileAdapter() {
		if (recordFileItemProvider == null) {
			recordFileItemProvider = new RecordFileItemProvider(this);
		}

		return recordFileItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.DataEvent} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataEventItemProvider dataEventItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.DataEvent}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDataEventAdapter() {
		if (dataEventItemProvider == null) {
			dataEventItemProvider = new DataEventItemProvider(this);
		}

		return dataEventItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.XMLSchema} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XMLSchemaItemProvider xmlSchemaItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.XMLSchema}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createXMLSchemaAdapter() {
		if (xmlSchemaItemProvider == null) {
			xmlSchemaItemProvider = new XMLSchemaItemProvider(this);
		}

		return xmlSchemaItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ComplexContentType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComplexContentTypeItemProvider complexContentTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ComplexContentType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createComplexContentTypeAdapter() {
		if (complexContentTypeItemProvider == null) {
			complexContentTypeItemProvider = new ComplexContentTypeItemProvider(this);
		}

		return complexContentTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.AllContent} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AllContentItemProvider allContentItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.AllContent}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAllContentAdapter() {
		if (allContentItemProvider == null) {
			allContentItemProvider = new AllContentItemProvider(this);
		}

		return allContentItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.SeqContent} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SeqContentItemProvider seqContentItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.SeqContent}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSeqContentAdapter() {
		if (seqContentItemProvider == null) {
			seqContentItemProvider = new SeqContentItemProvider(this);
		}

		return seqContentItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ChoiceContent} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChoiceContentItemProvider choiceContentItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ChoiceContent}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createChoiceContentAdapter() {
		if (choiceContentItemProvider == null) {
			choiceContentItemProvider = new ChoiceContentItemProvider(this);
		}

		return choiceContentItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ContentItem} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentItemItemProvider contentItemItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ContentItem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createContentItemAdapter() {
		if (contentItemItemProvider == null) {
			contentItemItemProvider = new ContentItemItemProvider(this);
		}

		return contentItemItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.GroupContent} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GroupContentItemProvider groupContentItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.GroupContent}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createGroupContentAdapter() {
		if (groupContentItemProvider == null) {
			groupContentItemProvider = new GroupContentItemProvider(this);
		}

		return groupContentItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ContentRestriction} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentRestrictionItemProvider contentRestrictionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ContentRestriction}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createContentRestrictionAdapter() {
		if (contentRestrictionItemProvider == null) {
			contentRestrictionItemProvider = new ContentRestrictionItemProvider(this);
		}

		return contentRestrictionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.SimpleContentType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SimpleContentTypeItemProvider simpleContentTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.SimpleContentType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSimpleContentTypeAdapter() {
		if (simpleContentTypeItemProvider == null) {
			simpleContentTypeItemProvider = new SimpleContentTypeItemProvider(this);
		}

		return simpleContentTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ExtendedDataElement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtendedDataElementItemProvider extendedDataElementItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ExtendedDataElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createExtendedDataElementAdapter() {
		if (extendedDataElementItemProvider == null) {
			extendedDataElementItemProvider = new ExtendedDataElementItemProvider(this);
		}

		return extendedDataElementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.DataRelationship} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataRelationshipItemProvider dataRelationshipItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.DataRelationship}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDataRelationshipAdapter() {
		if (dataRelationshipItemProvider == null) {
			dataRelationshipItemProvider = new DataRelationshipItemProvider(this);
		}

		return dataRelationshipItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.MixedContent} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MixedContentItemProvider mixedContentItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.MixedContent}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createMixedContentAdapter() {
		if (mixedContentItemProvider == null) {
			mixedContentItemProvider = new MixedContentItemProvider(this);
		}

		return mixedContentItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ContentReference} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentReferenceItemProvider contentReferenceItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ContentReference}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createContentReferenceAdapter() {
		if (contentReferenceItemProvider == null) {
			contentReferenceItemProvider = new ContentReferenceItemProvider(this);
		}

		return contentReferenceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.DataAction} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataActionItemProvider dataActionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.DataAction}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDataActionAdapter() {
		if (dataActionItemProvider == null) {
			dataActionItemProvider = new DataActionItemProvider(this);
		}

		return dataActionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ReadsColumnSet} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReadsColumnSetItemProvider readsColumnSetItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ReadsColumnSet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createReadsColumnSetAdapter() {
		if (readsColumnSetItemProvider == null) {
			readsColumnSetItemProvider = new ReadsColumnSetItemProvider(this);
		}

		return readsColumnSetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ContentAttribute} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentAttributeItemProvider contentAttributeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ContentAttribute}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createContentAttributeAdapter() {
		if (contentAttributeItemProvider == null) {
			contentAttributeItemProvider = new ContentAttributeItemProvider(this);
		}

		return contentAttributeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.TypedBy} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypedByItemProvider typedByItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.TypedBy}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTypedByAdapter() {
		if (typedByItemProvider == null) {
			typedByItemProvider = new TypedByItemProvider(this);
		}

		return typedByItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ReferenceTo} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferenceToItemProvider referenceToItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ReferenceTo}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createReferenceToAdapter() {
		if (referenceToItemProvider == null) {
			referenceToItemProvider = new ReferenceToItemProvider(this);
		}

		return referenceToItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.RestrictionOf} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RestrictionOfItemProvider restrictionOfItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.RestrictionOf}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRestrictionOfAdapter() {
		if (restrictionOfItemProvider == null) {
			restrictionOfItemProvider = new RestrictionOfItemProvider(this);
		}

		return restrictionOfItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ExtensionTo} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtensionToItemProvider extensionToItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ExtensionTo}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createExtensionToAdapter() {
		if (extensionToItemProvider == null) {
			extensionToItemProvider = new ExtensionToItemProvider(this);
		}

		return extensionToItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.DatatypeOf} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DatatypeOfItemProvider datatypeOfItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.DatatypeOf}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDatatypeOfAdapter() {
		if (datatypeOfItemProvider == null) {
			datatypeOfItemProvider = new DatatypeOfItemProvider(this);
		}

		return datatypeOfItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.HasContent} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HasContentItemProvider hasContentItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.HasContent}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createHasContentAdapter() {
		if (hasContentItemProvider == null) {
			hasContentItemProvider = new HasContentItemProvider(this);
		}

		return hasContentItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.WritesColumnSet} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WritesColumnSetItemProvider writesColumnSetItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.WritesColumnSet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createWritesColumnSetAdapter() {
		if (writesColumnSetItemProvider == null) {
			writesColumnSetItemProvider = new WritesColumnSetItemProvider(this);
		}

		return writesColumnSetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ProducesDataEvent} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProducesDataEventItemProvider producesDataEventItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ProducesDataEvent}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createProducesDataEventAdapter() {
		if (producesDataEventItemProvider == null) {
			producesDataEventItemProvider = new ProducesDataEventItemProvider(this);
		}

		return producesDataEventItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.DataSegment} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataSegmentItemProvider dataSegmentItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.DataSegment}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDataSegmentAdapter() {
		if (dataSegmentItemProvider == null) {
			dataSegmentItemProvider = new DataSegmentItemProvider(this);
		}

		return dataSegmentItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ContentElement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentElementItemProvider contentElementItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ContentElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createContentElementAdapter() {
		if (contentElementItemProvider == null) {
			contentElementItemProvider = new ContentElementItemProvider(this);
		}

		return contentElementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmt.modisco.omg.kdm.data.ManagesData} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ManagesDataItemProvider managesDataItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmt.modisco.omg.kdm.data.ManagesData}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createManagesDataAdapter() {
		if (managesDataItemProvider == null) {
			managesDataItemProvider = new ManagesDataItemProvider(this);
		}

		return managesDataItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (dataModelItemProvider != null) dataModelItemProvider.dispose();
		if (dataResourceItemProvider != null) dataResourceItemProvider.dispose();
		if (indexElementItemProvider != null) indexElementItemProvider.dispose();
		if (uniqueKeyItemProvider != null) uniqueKeyItemProvider.dispose();
		if (indexItemProvider != null) indexItemProvider.dispose();
		if (keyRelationItemProvider != null) keyRelationItemProvider.dispose();
		if (referenceKeyItemProvider != null) referenceKeyItemProvider.dispose();
		if (dataContainerItemProvider != null) dataContainerItemProvider.dispose();
		if (catalogItemProvider != null) catalogItemProvider.dispose();
		if (relationalSchemaItemProvider != null) relationalSchemaItemProvider.dispose();
		if (columnSetItemProvider != null) columnSetItemProvider.dispose();
		if (relationalTableItemProvider != null) relationalTableItemProvider.dispose();
		if (relationalViewItemProvider != null) relationalViewItemProvider.dispose();
		if (recordFileItemProvider != null) recordFileItemProvider.dispose();
		if (dataEventItemProvider != null) dataEventItemProvider.dispose();
		if (xmlSchemaItemProvider != null) xmlSchemaItemProvider.dispose();
		if (complexContentTypeItemProvider != null) complexContentTypeItemProvider.dispose();
		if (allContentItemProvider != null) allContentItemProvider.dispose();
		if (seqContentItemProvider != null) seqContentItemProvider.dispose();
		if (choiceContentItemProvider != null) choiceContentItemProvider.dispose();
		if (contentItemItemProvider != null) contentItemItemProvider.dispose();
		if (groupContentItemProvider != null) groupContentItemProvider.dispose();
		if (contentRestrictionItemProvider != null) contentRestrictionItemProvider.dispose();
		if (simpleContentTypeItemProvider != null) simpleContentTypeItemProvider.dispose();
		if (extendedDataElementItemProvider != null) extendedDataElementItemProvider.dispose();
		if (dataRelationshipItemProvider != null) dataRelationshipItemProvider.dispose();
		if (mixedContentItemProvider != null) mixedContentItemProvider.dispose();
		if (contentReferenceItemProvider != null) contentReferenceItemProvider.dispose();
		if (dataActionItemProvider != null) dataActionItemProvider.dispose();
		if (readsColumnSetItemProvider != null) readsColumnSetItemProvider.dispose();
		if (contentAttributeItemProvider != null) contentAttributeItemProvider.dispose();
		if (typedByItemProvider != null) typedByItemProvider.dispose();
		if (referenceToItemProvider != null) referenceToItemProvider.dispose();
		if (restrictionOfItemProvider != null) restrictionOfItemProvider.dispose();
		if (extensionToItemProvider != null) extensionToItemProvider.dispose();
		if (datatypeOfItemProvider != null) datatypeOfItemProvider.dispose();
		if (hasContentItemProvider != null) hasContentItemProvider.dispose();
		if (writesColumnSetItemProvider != null) writesColumnSetItemProvider.dispose();
		if (producesDataEventItemProvider != null) producesDataEventItemProvider.dispose();
		if (dataSegmentItemProvider != null) dataSegmentItemProvider.dispose();
		if (contentElementItemProvider != null) contentElementItemProvider.dispose();
		if (managesDataItemProvider != null) managesDataItemProvider.dispose();
	}

}
