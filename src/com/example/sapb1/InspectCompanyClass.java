package com.example.sapb1;

import java.lang.reflect.*;

public class InspectCompanyClass {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("检查 SAP B1 API 类的结构");
        System.out.println("========================================\n");

        // 检查 Company 类
        inspectClass("com.sap.smb.sbo.api.Company");

        // 检查 SBObob 类
        inspectClass("com.sap.smb.sbo.api.SBObob");

        // 检查 SBOCOMUtil 类
        inspectClass("com.sap.smb.sbo.api.SBOCOMUtil");

        // 检查 ICompany 接口
        inspectClass("com.sap.smb.sbo.api.ICompany");
    }

    private static void inspectClass(String className) {
        try {
            Class<?> clazz = Class.forName(className);

            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("  类: " + clazz.getSimpleName());
            System.out.println("╚════════════════════════════════════════╝");

            // 1. 构造函数
            System.out.println("\n【构造函数】");
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            if (constructors.length == 0) {
                System.out.println("  (无公共构造函数)");
            } else {
                for (Constructor<?> constructor : constructors) {
                    String modifier = Modifier.toString(constructor.getModifiers());
                    System.out.print("  " + modifier + " " + clazz.getSimpleName() + "(");

                    Class<?>[] paramTypes = constructor.getParameterTypes();
                    for (int i = 0; i < paramTypes.length; i++) {
                        System.out.print(paramTypes[i].getSimpleName());
                        if (i < paramTypes.length - 1) System.out.print(", ");
                    }
                    System.out.println(")");
                }
            }

            // 2. 静态方法
            System.out.println("\n【静态方法】");
            Method[] methods = clazz.getMethods();
            boolean hasStaticMethod = false;
            for (Method method : methods) {
                if (Modifier.isStatic(method.getModifiers()) &&
                        method.getDeclaringClass() == clazz) {
                    hasStaticMethod = true;
                    System.out.print("  static " + method.getReturnType().getSimpleName() + " ");
                    System.out.print(method.getName() + "(");

                    Class<?>[] paramTypes = method.getParameterTypes();
                    for (int i = 0; i < paramTypes.length; i++) {
                        System.out.print(paramTypes[i].getSimpleName());
                        if (i < paramTypes.length - 1) System.out.print(", ");
                    }
                    System.out.println(")");
                }
            }
            if (!hasStaticMethod) {
                System.out.println("  (无静态方法)");
            }

            // 3. 实例方法（只显示几个重要的）
            System.out.println("\n【关键实例方法】");
            String[] importantMethods = {"getCompany", "newCompany", "createCompany",
                    "getInstance", "connect", "getByKey"};
            boolean hasImportantMethod = false;
            for (Method method : methods) {
                String methodName = method.getName();
                for (String important : importantMethods) {
                    if (methodName.equalsIgnoreCase(important)) {
                        hasImportantMethod = true;
                        System.out.print("  " + method.getReturnType().getSimpleName() + " ");
                        System.out.print(method.getName() + "(");

                        Class<?>[] paramTypes = method.getParameterTypes();
                        for (int i = 0; i < paramTypes.length; i++) {
                            System.out.print(paramTypes[i].getSimpleName());
                            if (i < paramTypes.length - 1) System.out.print(", ");
                        }
                        System.out.println(")");
                    }
                }
            }
            if (!hasImportantMethod) {
                System.out.println("  (无关键方法)");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("\n✗ 类不存在: " + className);
        } catch (Exception e) {
            System.out.println("\n✗ 检查失败: " + e.getMessage());
        }
    }
}
/*
"C:\Program Files\SapMachine\JDK\17\bin\java.exe" "-Djava.library.path=C:\Program Files\SAP\SAP Business One DI API\JCO\LIB" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2025.2.5\lib\idea_rt.jar=64623" -Dfile.encoding=UTF-8 -classpath "C:\Users\Administrator\IdeaProjects\SAPB1DIConnectinJava\out\production\SAPB1DIConnectinJava;C:\Program Files\SAP\SAP Business One DI API\JCO\LIB\sboapi.jar;C:\Program Files\SAP\SAP Business One DI API\JCO\LIB\sbowrapper.jar" com.example.sapb1.InspectCompanyClass
========================================
检查 SAP B1 API 类的结构
========================================


╔════════════════════════════════════════╗
  类: Company
╚════════════════════════════════════════╝

【构造函数】
  public Company(Object)

【静态方法】
  (无静态方法)

【关键实例方法】
  int connect()

╔════════════════════════════════════════╗
  类: SBObob
╚════════════════════════════════════════╝

【构造函数】
  public SBObob(Object)

【静态方法】
  (无静态方法)

【关键实例方法】
  (无关键方法)

╔════════════════════════════════════════╗
  类: SBOCOMUtil
╚════════════════════════════════════════╝

【构造函数】
  public SBOCOMUtil()

【静态方法】
  static ICompany newCompany()
  static ILandedCostsCodes newLandedCostsCodes(ICompany)
  static IPaymentTermsTypes newPaymentTermsTypes(ICompany)
  static IBusinessPartners newBusinessPartners(ICompany)
  static IDeductionTaxHierarchies newDeductionTaxHierarchies(ICompany)
  static IChecksforPayment newChecksforPayment(ICompany)
  static IDeductionTaxGroups newDeductionTaxGroups(ICompany)
  static ICreditPaymentMethods newCreditPaymentMethods(ICompany)
  static IAdditionalExpenses newAdditionalExpenses(ICompany)
  static ISalesTaxAuthoritiesTypes newSalesTaxAuthoritiesTypes(ICompany)
  static IBudgetDistribution newBudgetDistribution(ICompany)
  static ISalesOpportunities newSalesOpportunities(ICompany)
  static ICreditCardPayments newCreditCardPayments(ICompany)
  static IActivityLocations newActivityLocations(ICompany)
  static IUserDefaultGroups newUserDefaultGroups(ICompany)
  static ISalesTaxAuthorities newSalesTaxAuthorities(ICompany)
  static ICommissionGroups newCommissionGroups(ICompany)
  static IWarehouseLocations newWarehouseLocations(ICompany)
  static IBillOfExchangeTransaction newBillOfExchangeTransaction(ICompany)
  static IAccountSegmentationCategories newAccountSegmentationCategories(ICompany)
  static ICustomerEquipmentCards newCustomerEquipmentCards(ICompany)
  static IAccountSegmentations newAccountSegmentations(ICompany)
  static IFactoringIndicators newFactoringIndicators(ICompany)
  static IWithholdingTaxCodes newWithholdingTaxCodes(ICompany)
  static IContractTemplates newContractTemplates(ICompany)
  static IProductionOrders newProductionOrders(ICompany)
  static IKnowledgeBaseSolutions newKnowledgeBaseSolutions(ICompany)
  static IServiceContracts newServiceContracts(ICompany)
  static IPaymentRunExport newPaymentRunExport(ICompany)
  static IMaterialRevaluation newMaterialRevaluation(ICompany)
  static IWizardPaymentMethods newWizardPaymentMethods(ICompany)
  static IMultiLanguageTranslations newMultiLanguageTranslations(ICompany)
  static IClosingDateProcedure newClosingDateProcedure(ICompany)
  static IHouseBankAccounts newHouseBankAccounts(ICompany)
  static IDynamicSystemStrings newDynamicSystemStrings(ICompany)
  static IUserPermissionTree newUserPermissionTree(ICompany)
  static IBPFiscalRegistryID newBPFiscalRegistryID(ICompany)
  static IBusinessPartnerGroups newBusinessPartnerGroups(ICompany)
  static IFormattedSearches newFormattedSearches(ICompany)
  static IPaymentRunExport getPaymentRunExport(ICompany, Integer)
  static IItems newItems(ICompany)
  static IVatGroups newVatGroups(ICompany)
  static IBanks newBanks(ICompany)
  static IPriceLists newPriceLists(ICompany)
  static ISpecialPrices newSpecialPrices(ICompany)
  static IChartOfAccounts newChartOfAccounts(ICompany)
  static IBankPages newBankPages(ICompany)
  static ICreditCards newCreditCards(ICompany)
  static IUsers newUsers(ICompany)
  static IStockTaking newStockTaking(ICompany)
  static IItemProperties newItemProperties(ICompany)
  static ICurrencies newCurrencies(ICompany)
  static IManufacturers newManufacturers(ICompany)
  static IJournalEntries newJournalEntries(ICompany)
  static IContacts newContacts(ICompany)
  static ILengthMeasures newLengthMeasures(ICompany)
  static IShippingTypes newShippingTypes(ICompany)
  static IProductTrees newProductTrees(ICompany)
  static ISalesPersons newSalesPersons(ICompany)
  static IWarehouses newWarehouses(ICompany)
  static IItemGroups newItemGroups(ICompany)
  static IWeightMeasures newWeightMeasures(ICompany)
  static ICustomsGroups newCustomsGroups(ICompany)
  static IBudgetScenarios newBudgetScenarios(ICompany)
  static ISalesStages newSalesStages(ICompany)
  static IWorkOrders newWorkOrders(ICompany)
  static IStockTransfer newStockTransfer(ICompany)
  static IStockTransfer newStockTransfer(ICompany, Integer)
  static IAlternateCatNum newAlternateCatNum(ICompany)
  static IBudget newBudget(ICompany)
  static IPickLists newPickLists(ICompany)
  static IUserTablesMD newUserTablesMD(ICompany)
  static IUserFieldsMD newUserFieldsMD(ICompany)
  static IDunningLetters newDunningLetters(ICompany)
  static IForms1099 newForms1099(ICompany)
  static IBPPriorities newBPPriorities(ICompany)
  static IInventoryCycles newInventoryCycles(ICompany)
  static IQueryCategories newQueryCategories(ICompany)
  static IActivityTypes newActivityTypes(ICompany)
  static ISalesTaxCodes newSalesTaxCodes(ICompany)
  static ITeams newTeams(ICompany)
  static ITerritories newTerritories(ICompany)
  static IIndustries newIndustries(ICompany)
  static ISalesForecast newSalesForecast(ICompany)
  static IActivityStatus newActivityStatus(ICompany)
  static IUserObjectsMD newUserObjectsMD(ICompany)
  static IQueue newQueue(ICompany)
  static IPackagesTypes newPackagesTypes(ICompany)
  static IRelationships newRelationships(ICompany)
  static INotaFiscalCFOP newNotaFiscalCFOP(ICompany)
  static INotaFiscalUsage newNotaFiscalUsage(ICompany)
  static IBusinessPlaces newBusinessPlaces(ICompany)
  static IChooseFromList newChooseFromList(ICompany)
  static ILocalEra newLocalEra(ICompany)
  static IAttachments2 newAttachments2(ICompany)
  static INotaFiscalCST newNotaFiscalCST(ICompany)
  static IUserLanguages newUserLanguages(ICompany)
  static IPayments newPayments(ICompany, Integer)
  static IUserTablesMD getUserTablesMD(ICompany, String)
  static IRecordset newRecordset(ICompany)
  static IUserFieldsMD getUserFieldsMD(ICompany, String, Integer)
  static ICounties newCounties(ICompany)
  static IDocuments newDocuments(ICompany, Integer)
  static IEmployeesInfo newEmployeesInfo(ICompany)
  static IUserQueries newUserQueries(ICompany)
  static IServiceCalls newServiceCalls(ICompany)
  static IUserKeysMD newUserKeysMD(ICompany)
  static IBusinessPartners getBusinessPartners(ICompany, String)
  static IWarehouseLocations getWarehouseLocations(ICompany, Integer)
  static IMaterialRevaluation getMaterialRevaluation(ICompany, Integer)
  static IChecksforPayment getChecksforPayment(ICompany, Integer)
  static IFormattedSearches getFormattedSearches(ICompany, Integer)
  static IDeductionTaxHierarchies getDeductionTaxHierarchies(ICompany, Integer)
  static IAlertManagementService newAlertManagementService(ICompanyService)
  static IBudgetDistribution getBudgetDistribution(ICompany, Integer)
  static IProductionOrders getProductionOrders(ICompany, Integer)
  static IFormPreferencesService newFormPreferencesService(ICompanyService)
  static IUserDefaultGroups getUserDefaultGroups(ICompany, String)
  static IExternalCallsService newExternalCallsService(ICompanyService)
  static ICountriesService newCountriesService(ICompanyService)
  static IActivityLocations getActivityLocations(ICompany, Integer)
  static IFactoringIndicators getFactoringIndicators(ICompany, String)
  static IUserPermissionTree getUserPermissionTree(ICompany, String)
  static IMultiLanguageTranslations getMultiLanguageTranslations(ICompany, Integer)
  static IBusinessPartnerGroups getBusinessPartnerGroups(ICompany, Integer)
  static IDeductionTaxGroups getDeductionTaxGroups(ICompany, Integer)
  static IApprovalStagesService newApprovalStagesService(ICompanyService)
  static IDynamicSystemStrings getDynamicSystemStrings(ICompany, String, String, String)
  static IHouseBankAccounts getHouseBankAccounts(ICompany, Integer)
  static IBusinessPartnersService newBusinessPartnersService(ICompanyService)
  static IWizardPaymentMethods getWizardPaymentMethods(ICompany, String)
  static ISalesTaxAuthorities getSalesTaxAuthorities(ICompany, String, Integer)
  static IBillOfExchangeTransaction getBillOfExchangeTransaction(ICompany, Integer)
  static IApprovalTemplatesService newApprovalTemplatesService(ICompanyService)
  static IAccountSegmentations getAccountSegmentations(ICompany, Integer)
  static IReportFilterService newReportFilterService(ICompanyService)
  static ISalesTaxAuthoritiesTypes getSalesTaxAuthoritiesTypes(ICompany, Integer)
  static IBankStatementsService newBankStatementsService(ICompanyService)
  static ICustomsDeclarationService newCustomsDeclarationService(ICompanyService)
  static IBPFiscalRegistryID getBPFiscalRegistryID(ICompany, Integer)
  static IServiceCallStatusService newServiceCallStatusService(ICompanyService)
  static IContractTemplates getContractTemplates(ICompany, String)
  static ICustomerEquipmentCards getCustomerEquipmentCards(ICompany, Integer)
  static IPaymentRunExport getPaymentRunExports(ICompany, Integer)
  static IBOEDocumentTypesService newBOEDocumentTypesService(ICompanyService)
  static IBOEInstructionsService newBOEInstructionsService(ICompanyService)
  static IDepartmentsService newDepartmentsService(ICompanyService)
  static IAccountSegmentationCategories getAccountSegmentationCategories(ICompany, Integer, String)
  static ICommissionGroups getCommissionGroups(ICompany, Integer)
  static ILandedCostsCodes getLandedCostsCodes(ICompany, String)
  static ICreditPaymentMethods getCreditPaymentMethods(ICompany, Integer)
  static IAdditionalExpenses getAdditionalExpenses(ICompany, Integer)
  static IClosingDateProcedure getClosingDateProcedure(ICompany, Integer)
  static IBillOfExchangeTransaction newBillOfExchangeTransactions(ICompany)
  static ICreditCardPayments getCreditCardPayments(ICompany, String)
  static IAlternativeItemsService newAlternativeItemsService(ICompanyService)
  static IAccountCategoryService newAccountCategoryService(ICompanyService)
  static IPredefinedTextsService newPredefinedTextsService(ICompanyService)
  static IServiceCallProblemTypesService newServiceCallProblemTypesService(ICompanyService)
  static IPaymentTermsTypes getPaymentTermsTypes(ICompany, Integer)
  static IKnowledgeBaseSolutions getKnowledgeBaseSolutions(ICompany, Integer)
  static IReportLayoutsService newReportLayoutsService(ICompanyService)
  static IBOEPortfoliosService newBOEPortfoliosService(ICompanyService)
  static ISalesOpportunities getSalesOpportunities(ICompany, Integer)
  static IWithholdingTaxCodes getWithholdingTaxCodes(ICompany, String)
  static IEmployeeRolesSetupService newEmployeeRolesSetupService(ICompanyService)
  static IServiceContracts getServiceContracts(ICompany, Integer)
  static IElectronicFileFormatsService newElectronicFileFormatsService(ICompanyService)
  static IBrazilFuelIndexersService newBrazilFuelIndexersService(ICompanyService)
  static ICertificateSeriesService newCertificateSeriesService(ICompanyService)
  static IBlanketAgreementsService newBlanketAgreementsService(ICompanyService)
  static ILandedCostsService newLandedCostsService(ICompanyService)
  static ICheckLinesService newCheckLinesService(ICompanyService)
  static ITargetGroupsService newTargetGroupsService(ICompanyService)
  static IBrazilMultiIndexersService newBrazilMultiIndexersService(ICompanyService)
  static IBatchNumberDetailsService newBatchNumberDetailsService(ICompanyService)
  static IDepreciationTypePoolsService newDepreciationTypePoolsService(ICompanyService)
  static IExternalReconciliationsService newExternalReconciliationsService(ICompanyService)
  static IEmployeeIDTypeService newEmployeeIDTypeService(ICompanyService)
  static IDimensionsService newDimensionsService(ICompanyService)
  static INCMCodesSetupService newNCMCodesSetupService(ICompanyService)
  static ITransactionCodesService newTransactionCodesService(ICompanyService)
  static IDashboardPackagesService newDashboardPackagesService(ICompanyService)
  static ICreditLinesService newCreditLinesService(ICompanyService)
  static IApprovalRequestsService newApprovalRequestsService(ICompanyService)
  static IDistributionRulesService newDistributionRulesService(ICompanyService)
  static IServiceGroupsService newServiceGroupsService(ICompanyService)
  static IBrazilNumericIndexersService newBrazilNumericIndexersService(ICompanyService)
  static IRetornoCodesService newRetornoCodesService(ICompanyService)
  static IPaymentBlocksService newPaymentBlocksService(ICompanyService)
  static IDeterminationCriteriasService newDeterminationCriteriasService(ICompanyService)
  static IDeductionTaxSubGroupsService newDeductionTaxSubGroupsService(ICompanyService)
  static IMaterialGroupsService newMaterialGroupsService(ICompanyService)
  static IChangeLogsService newChangeLogsService(ICompanyService)
  static IExtendedTranslationsService newExtendedTranslationsService(ICompanyService)
  static INatureOfAssesseesService newNatureOfAssesseesService(ICompanyService)
  static ILegalDataService newLegalDataService(ICompanyService)
  static IEnhancedDiscountGroupsService newEnhancedDiscountGroupsService(ICompanyService)
  static IBinLocationsService newBinLocationsService(ICompanyService)
  static IServiceCallTypesService newServiceCallTypesService(ICompanyService)
  static IDNFCodeSetupService newDNFCodeSetupService(ICompanyService)
  static IBrazilStringIndexersService newBrazilStringIndexersService(ICompanyService)
  static ICashDiscountsService newCashDiscountsService(ICompanyService)
  static IDunningTermsService newDunningTermsService(ICompanyService)
  static ICashFlowLineItemsService newCashFlowLineItemsService(ICompanyService)
  static IAccrualTypesService newAccrualTypesService(ICompanyService)
  static ICampaignsService newCampaignsService(ICompanyService)
  static IDepreciationAreasService newDepreciationAreasService(ICompanyService)
  static IFAAccountDeterminationsService newFAAccountDeterminationsService(ICompanyService)
  static IPartnersSetupsService newPartnersSetupsService(ICompanyService)
  static IEmployeeTransfersService newEmployeeTransfersService(ICompanyService)
  static IServiceCallOriginsService newServiceCallOriginsService(ICompanyService)
  static IFinancialYearsService newFinancialYearsService(ICompanyService)
  static IReportTypesService newReportTypesService(ICompanyService)
  static IBrazilBeverageIndexersService newBrazilBeverageIndexersService(ICompanyService)
  static IJournalEntryDocumentTypeService newJournalEntryDocumentTypeService(ICompanyService)
  static ISerialNumberDetailsService newSerialNumberDetailsService(ICompanyService)
  static IOccurrenceCodesService newOccurrenceCodesService(ICompanyService)
  static IProfitCentersService newProfitCentersService(ICompanyService)
  static IDepreciationTypesService newDepreciationTypesService(ICompanyService)
  static IBinLocationFieldsService newBinLocationFieldsService(ICompanyService)
  static IAssetClassesService newAssetClassesService(ICompanyService)
  static IMaterialRevaluationFIFOService newMaterialRevaluationFIFOService(ICompanyService)
  static ICostCenterTypesService newCostCenterTypesService(ICompanyService)
  static IBinLocationAttributesService newBinLocationAttributesService(ICompanyService)
  static ITaxWebSitesService newTaxWebSitesService(ICompanyService)
  static IServiceTaxPostingService newServiceTaxPostingService(ICompanyService)
  static IGLAccountAdvancedRulesService newGLAccountAdvancedRulesService(ICompanyService)
  static IWarehouseSublevelCodesService newWarehouseSublevelCodesService(ICompanyService)
  static ITaxCodeDeterminationsService newTaxCodeDeterminationsService(ICompanyService)
  static IActivitiesService newActivitiesService(ICompanyService)
  static IQueryAuthGroupService newQueryAuthGroupService(ICompanyService)
  static ITransportationDocumentService newTransportationDocumentService(ICompanyService)
  static IPOSDailySummaryService newPOSDailySummaryService(ICompanyService)
  static ICycleCountDeterminationsService newCycleCountDeterminationsService(ICompanyService)
  static IWitholdingTaxDefinitionService newWitholdingTaxDefinitionService(ICompanyService)
  static IAttributeGroupsService newAttributeGroupsService(ICompanyService)
  static ITaxInvoiceReportService newTaxInvoiceReportService(ICompanyService)
  static IProjectManagementService newProjectManagementService(ICompanyService)
  static ICampaignResponseTypeService newCampaignResponseTypeService(ICompanyService)
  static IAssetGroupsService newAssetGroupsService(ICompanyService)
  static IMaterialRevaluationSNBService newMaterialRevaluationSNBService(ICompanyService)
  static IInventoryCountingsService newInventoryCountingsService(ICompanyService)
  static IResourcePropertiesService newResourcePropertiesService(ICompanyService)
  static ISingleUserConnectionService newSingleUserConnectionService(ICompanyService)
  static IWebClientPreferenceService newWebClientPreferenceService(ICompanyService)
  static IFixedAssetItemsService newFixedAssetItemsService(ICompanyService)
  static IEmployeePositionService newEmployeePositionService(ICompanyService)
  static INFTaxCategoriesService newNFTaxCategoriesService(ICompanyService)
  static IRecurringTransactionService newRecurringTransactionService(ICompanyService)
  static IExpenseTypeService newExpenseTypeService(ICompanyService)
  static IEWBTransporterService newEWBTransporterService(ICompanyService)
  static IActivitySubjectService newActivitySubjectService(ICompanyService)
  static ITerminationReasonService newTerminationReasonService(ICompanyService)
  static IUnitOfMeasurementGroupsService newUnitOfMeasurementGroupsService(ICompanyService)
  static IEmployeeStatusService newEmployeeStatusService(ICompanyService)
  static ITaxCodeDeterminationsTCDService newTaxCodeDeterminationsTCDService(ICompanyService)
  static IResourceGroupsService newResourceGroupsService(ICompanyService)
  static IResourceCapacitiesService newResourceCapacitiesService(ICompanyService)
  static IValueMappingService newValueMappingService(ICompanyService)
  static IMobileAddOnSettingService newMobileAddOnSettingService(ICompanyService)
  static IWorkflowTaskService newWorkflowTaskService(ICompanyService)
  static IDefaultElementsforCRService newDefaultElementsforCRService(ICompanyService)
  static IWebClientListviewFilterService newWebClientListviewFilterService(ICompanyService)
  static IWebClientNotificationService newWebClientNotificationService(ICompanyService)
  static IGovPayCodesService newGovPayCodesService(ICompanyService)
  static IAssetDepreciationGroupsService newAssetDepreciationGroupsService(ICompanyService)
  static ICostElementService newCostElementService(ICompanyService)
  static IUserGroupService newUserGroupService(ICompanyService)
  static IRouteStagesService newRouteStagesService(ICompanyService)
  static IWebClientVariantService newWebClientVariantService(ICompanyService)
  static IWebClientVariantGroupService newWebClientVariantGroupService(ICompanyService)
  static IRecurringPostingsService newRecurringPostingsService(ICompanyService)
  static IInventoryPostingsService newInventoryPostingsService(ICompanyService)
  static IEmailGroupsService newEmailGroupsService(ICompanyService)
  static IActivityRecipientListsService newActivityRecipientListsService(ICompanyService)
  static IMobileAppService newMobileAppService(ICompanyService)
  static IIndiaSacCodeService newIndiaSacCodeService(ICompanyService)
  static IWebClientDashboardService newWebClientDashboardService(ICompanyService)
  static IWebClientRecentActivityService newWebClientRecentActivityService(ICompanyService)
  static IWebClientFormSettingService newWebClientFormSettingService(ICompanyService)
  static IPaymentCalculationService newPaymentCalculationService(ICompanyService)
  static ITrackingNotesService newTrackingNotesService(ICompanyService)
  static IUnitOfMeasurementsService newUnitOfMeasurementsService(ICompanyService)
  static IWebClientBookmarkTileService newWebClientBookmarkTileService(ICompanyService)
  static IPostingTemplatesService newPostingTemplatesService(ICompanyService)
  static IResourcesService newResourcesService(ICompanyService)
  static ISensitiveDataAccessService newSensitiveDataAccessService(ICompanyService)
  static IWebClientLaunchpadService newWebClientLaunchpadService(ICompanyService)
  static IRoutingDateCalculationService newRoutingDateCalculationService(ICompanyService)
  static IElectronicDocumentService newElectronicDocumentService(ICompanyService)
  static IIntrastatConfigurationService newIntrastatConfigurationService(ICompanyService)
  static IInventoryOpeningBalancesService newInventoryOpeningBalancesService(ICompanyService)
  static IFiscalPrinterService newFiscalPrinterService(ICompanyService)
  static IInternalReconciliationsService newInternalReconciliationsService(ICompanyService)
  static IImportDeterminationService newImportDeterminationService(ICompanyService)
  static ICentralBankIndicatorService newCentralBankIndicatorService(ICompanyService)
  static ISBOMailerService newSBOMailerService(ICompanyService)
  static ITaxReplStateSubService newTaxReplStateSubService(ICompanyService)
  static IISDInvoicesService newISDInvoicesService(ICompanyService)
  static IIdentificationCodeService newIdentificationCodeService(ICompanyService)
  static IISDRecipientInvoicesService newISDRecipientInvoicesService(ICompanyService)
  static IBPVatExemptionsService newBPVatExemptionsService(ICompanyService)
  static ITaxExemptReasonService newTaxExemptReasonService(ICompanyService)
  static ISpecificWTHAmountsService newSpecificWTHAmountsService(ICompanyService)
  static IWTaxTypeCodeService newWTaxTypeCodeService(ICompanyService)
  static IExceptionalEventService newExceptionalEventService(ICompanyService)
  static ITSRExceptionalEventService newTSRExceptionalEventService(ICompanyService)
  static IDeductibleTaxService newDeductibleTaxService(ICompanyService)
  static IBEMReplicationPeriodService newBEMReplicationPeriodService(ICompanyService)
  static IExportDeterminationService newExportDeterminationService(ICompanyService)
  static IPaymentReasonCodeService newPaymentReasonCodeService(ICompanyService)
  static IISDCreditMemosService newISDCreditMemosService(ICompanyService)
  static IEmploymentCategoryService newEmploymentCategoryService(ICompanyService)
  static IAssetRevaluationService newAssetRevaluationService(ICompanyService)
  static IAssetDocumentService newAssetDocumentService(ICompanyService, Integer)
  static IValueMappingCommunicationService newValueMappingCommunicationService(ICompanyService)
  static IIntegrationPackagesConfigureService newIntegrationPackagesConfigureService(ICompanyService)
  static IServiceCallProblemSubTypesService newServiceCallProblemSubTypesService(ICompanyService)
  static IProjectManagementTimeSheetService newProjectManagementTimeSheetService(ICompanyService)
  static ISalesOpportunityCompetitorsSetupService newSalesOpportunityCompetitorsSetupService(ICompanyService)
  static ISalesOpportunityReasonsSetupService newSalesOpportunityReasonsSetupService(ICompanyService)
  static IBankChargesAllocationCodesService newBankChargesAllocationCodesService(ICompanyService)
  static ISalesOpportunityInterestsSetupService newSalesOpportunityInterestsSetupService(ICompanyService)
  static IElectronicCommunicationActionService newElectronicCommunicationActionService(ICompanyService)
  static IProjectManagementConfigurationService newProjectManagementConfigurationService(ICompanyService)
  static IElectronicCommunicationActionsService newElectronicCommunicationActionsService(ICompanyService)
  static IBusinessPartnerPropertiesService newBusinessPartnerPropertiesService(ICompanyService)
  static IServiceCallSolutionStatusService newServiceCallSolutionStatusService(ICompanyService)
  static ISalesOpportunitySourcesSetupService newSalesOpportunitySourcesSetupService(ICompanyService)
  static IRecurringTransactionTemplatesService newRecurringTransactionTemplatesService(ICompanyService)
  static IJournalVouchers newJournalVouchers(ICompany)
  static IJournalEntries getJournalEntries(ICompany, Integer)
  static IBanks getBanks(ICompany, Integer)
  static IUsers getUsers(ICompany, Integer)
  static IPriceLists getPriceLists(ICompany, String)
  static IItems getItems(ICompany, String)
  static IChartOfAccounts getChartOfAccounts(ICompany, String)
  static IVatGroups getVatGroups(ICompany, String)
  static ISpecialPrices getSpecialPrices(ICompany, String, String)
  static IItemProperties getItemProperties(ICompany, Integer)
  static ICreditCards getCreditCards(ICompany, Integer)
  static IContacts getContacts(ICompany, Integer)
  static IWeightMeasures getWeightMeasures(ICompany, Integer)
  static IProductTrees getProductTrees(ICompany, String)
  static IWorkOrders getWorkOrders(ICompany, Integer)
  static IStockTransfer getStockTransfer(ICompany, Integer)
  static IStockTransfer getStockTransfer(ICompany, Integer, Integer)
  static ICurrencies getCurrencies(ICompany, String)
  static IManufacturers getManufacturers(ICompany, Integer)
  static IAlternateCatNum getAlternateCatNum(ICompany, String, String, String)
  static ILengthMeasures getLengthMeasures(ICompany, Integer)
  static ICustomsGroups getCustomsGroups(ICompany, Integer)
  static IStockTaking getStockTaking(ICompany, String, String)
  static IBankPages getBankPages(ICompany, String, Integer)
  static IShippingTypes getShippingTypes(ICompany, Integer)
  static IItemGroups getItemGroups(ICompany, Integer)
  static ISalesPersons getSalesPersons(ICompany, Integer)
  static IWarehouses getWarehouses(ICompany, String)
  static ISalesStages getSalesStages(ICompany, Integer)
  static IBudget getBudget(ICompany, Integer)
  static IBudgetScenarios getBudgetScenarios(ICompany, Integer)
  static IQueryCategories getQueryCategories(ICompany, Integer)
  static IForms1099 getForms1099(ICompany, Integer)
  static IBPPriorities getBPPriorities(ICompany, Integer)
  static ISalesTaxCodes getSalesTaxCodes(ICompany, String)
  static IDunningLetters getDunningLetters(ICompany, Integer)
  static IPickLists getPickLists(ICompany, Integer)
  static IActivityTypes getActivityTypes(ICompany, Integer)
  static IMessages newMessages(ICompany)
  static IInventoryCycles getInventoryCycles(ICompany, Integer)
  static ITeams getTeams(ICompany, Integer)
  static IActivityStatus getActivityStatus(ICompany, Integer)
  static IChooseFromList getChooseFromList(ICompany, String)
  static IIndustries getIndustries(ICompany, Integer)
  static IEmployeesInfo getEmployeesInfo(ICompany, Integer)
  static ITerritories getTerritories(ICompany, Integer)
  static IAttachments2 getAttachments2(ICompany, Integer)
  static ISalesForecast getSalesForecast(ICompany, Integer)
  static IUserLanguages getUserLanguages(ICompany, Integer)
  static IPackagesTypes getPackagesTypes(ICompany, Integer)
  static IUserQueries getUserQueries(ICompany, Integer, Integer)
  static IServiceCalls getServiceCalls(ICompany, Integer)
  static IUserObjectsMD getUserObjectsMD(ICompany, String)
  static IRelationships getRelationships(ICompany, Integer)
  static IUserKeysMD getUserKeysMD(ICompany, String, Integer)
  static IRecordset runRecordsetQuery(ICompany, String)
  static IBusinessPlaces getBusinessPlaces(ICompany, Integer)
  static IUserTablesMD getUserTablesMDs(ICompany, String)
  static IRecordsetEx newRecordsetEx(ICompany)
  static INotaFiscalUsage getNotaFiscalUsage(ICompany, Integer)
  static IMessagesService newMessagesService(ICompanyService)
  static IStockTaking newStockTakings(ICompany)
  static IPayments getPayments(ICompany, Integer, Integer)
  static ICounties getCounties(ICompany, Integer)
  static IDocuments getDocuments(ICompany, Integer, Integer)
  static IUserFieldsMD getUserFieldsMDs(ICompany, String, Integer)
  static ISeriesService newSeriesService(ICompanyService)
  static ISBObob getSBObob(ICompany)
  static INotaFiscalCFOP getNotaFiscalCFOP(ICompany, Integer)
  static ILocalEra getLocalEra(ICompany, String)
  static INotaFiscalCST getNotaFiscalCST(ICompany, Integer)
  static IBranchesService newBranchesService(ICompanyService)
  static IAccountsService newAccountsService(ICompanyService)
  static IUserMenuService newUserMenuService(ICompanyService)
  static IProjectsService newProjectsService(ICompanyService)
  static ISectionsService newSectionsService(ICompanyService)
  static IStatesService newStatesService(ICompanyService)
  static IBOELinesService newBOELinesService(ICompanyService)
  static IDepositsService newDepositsService(ICompanyService)
  static IGTIsService newGTIsService(ICompanyService)
  static INFModelsService newNFModelsService(ICompanyService)
  static ICockpitsService newCockpitsService(ICompanyService)
  static IKPIsService newKPIsService(ICompanyService)
  static IBarCodesService newBarCodesService(ICompanyService)
  static IHolidayService newHolidayService(ICompanyService)
  static IAddressService newAddressService(ICompanyService)
  static IQRCodeService newQRCodeService(ICompanyService)
  static ICESTCodeService newCESTCodeService(ICompanyService)
  static IEBooksService newEBooksService(ICompanyService)
  static ICIGCodeService newCIGCodeService(ICompanyService)
  static ICUPCodeService newCUPCodeService(ICompanyService)
  static IIndiaHsnService newIndiaHsnService(ICompanyService)
  static IGendersService newGendersService(ICompanyService)
  static IQueue getQueue(ICompany, String)
  static void release()

【关键实例方法】
  ICompany newCompany()

╔════════════════════════════════════════╗
  类: ICompany
╚════════════════════════════════════════╝

【构造函数】
  (无公共构造函数)

【静态方法】
  (无静态方法)

【关键实例方法】
  int connect()

Process finished with exit code 0

 */