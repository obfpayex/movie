package com.obf.movie.domain;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@SequenceGenerator(
    name = "transactionsSeq",
    sequenceName = "transactions_seq",
    allocationSize = 1,
    schema = "MOVIEDBA"
)
@Table(
    name = "transactions",
    schema = "MOVIEDBA"
)
@Cache(
    usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE
)
public class Transaction implements Serializable {
    @NotNull
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "transactionsSeq"
    )
    @Column(
        name = "oid",
        nullable = false
    )
    private Long oid;
    @NotNull
    @Size(
        max = 250
    )
    @Column(
        name = "session_id",
        nullable = false
    )
    private String sessionId;
    @NotNull
    @Size(
        max = 250
    )
    @Column(
        name = "client_tx_id",
        nullable = false
    )
    private String clientTxId;
    @Size(
        max = 250
    )
    @Column(
        name = "client_session_id"
    )
    private String clientSessionId;
    @NotNull
    @Column(
        name = "type",
        nullable = false
    )
    private Long type;
    @Size(
        max = 250
    )
    @Column(
        name = "tx_ref"
    )
    private String txRef;
    @Size(
        max = 250
    )
    @Column(
        name = "authcode"
    )
    private String authcode;
    @NotNull
    @Column(
        name = "card_info_oid",
        nullable = false
    )
    private Long cardInfoOid;
    @Column(
        name = "card_info_parent_oid"
    )
    private Long cardInfoParentOid;
    @NotNull
    @Size(
        max = 128
    )
    @Column(
        name = "hashed_pan",
        nullable = false
    )
    private String hashedPan;
    @Column(
        name = "expiry_date"
    )
    private Date expiryDate;
    @NotNull
    @Column(
        name = "card_product_oid",
        nullable = false
    )
    private Long cardProductOid;
    @NotNull
    @Size(
        max = 5
    )
    @Column(
        name = "status",
        nullable = false
    )
    private String status;
    @NotNull
    @Column(
        name = "amount",
        nullable = false
    )
    private Long amount;
    @Column(
        name = "issuer_oid"
    )
    private Long issuerOid;
    @Column(
        name = "provider_oid"
    )
    private Long providerOid;
    @Column(
        name = "card_profile_oid"
    )
    private Long cardProfileOid;
    @Size(
        max = 128
    )
    @Column(
        name = "merchantid"
    )
    private String merchantid;
    @Size(
        max = 128
    )
    @Column(
        name = "storeid"
    )
    private String storeid;
    @NotNull
    @Size(
        max = 128
    )
    @Column(
        name = "terminalid",
        nullable = false
    )
    private String terminalid;
    @Size(
        max = 128
    )
    @Column(
        name = "acquirerid"
    )
    private String acquirerid;
    @NotNull
    @Size(
        max = 3
    )
    @Column(
        name = "currency_iso",
        nullable = false
    )
    private String currencyIso;
    @NotNull
    @Column(
        name = "created",
        nullable = false
    )
    private Date created;
    @NotNull
    @Size(
        max = 40
    )
    @Column(
        name = "created_by",
        nullable = false
    )
    private String createdBy;
    @Column(
        name = "modified"
    )
    private Date modified;
    @Size(
        max = 40
    )
    @Column(
        name = "modified_by"
    )
    private String modifiedBy;
    @Column(
        name = "completed"
    )
    private Date completed;
    @Size(
        max = 4000
    )
    @Column(
        name = "additional_data"
    )
    private String additionalData;
    @Column(
        name = "eod_batch_oid"
    )
    private Long eodBatchOid;
    @Column(
        name = "cancelled"
    )
    private Date cancelled;
    @Size(
        max = 40
    )
    @Column(
        name = "cancelled_by"
    )
    private String cancelledBy;
    @Size(
        max = 250
    )
    @Column(
        name = "client_tx_id_capt"
    )
    private String clientTxIdCapt;
    @Size(
        max = 250
    )
    @Column(
        name = "client_tx_id_rev"
    )
    private String clientTxIdRev;
    @Size(
        max = 250
    )
    @Column(
        name = "capture_client_session_id"
    )
    private String captureClientSessionId;
    @Size(
        max = 50
    )
    @Column(
        name = "capture_processing_code"
    )
    private String captureProcessingCode;
    @Size(
        max = 50
    )
    @Column(
        name = "capture_service_type"
    )
    private String captureServiceType;
    @Size(
        max = 50
    )
    @Column(
        name = "capture_function_code"
    )
    private String captureFunctionCode;
    @Column(
        name = "flag"
    )
    private Boolean flag;
    @NotNull
    @Column(
        name = "reversable",
        nullable = false
    )
    private Boolean reversable;
    @NotNull
    @Column(
        name = "offline_tx",
        nullable = false
    )
    private Boolean offlineTx;
    @Size(
        max = 64
    )
    @Column(
        name = "client_terminal_ref"
    )
    private String clientTerminalRef;
    @Size(
        max = 64
    )
    @Column(
        name = "client_transmission_date"
    )
    private String clientTransmissionDate;
    @Size(
        max = 64
    )
    @Column(
        name = "client_transmission_dateformat"
    )
    private String clientTransmissionDateformat;
    @Size(
        max = 256
    )
    @Column(
        name = "stan"
    )
    private String stan;
    @Column(
        name = "error_reason_code"
    )
    private Long errorReasonCode;
    @Size(
        max = 250
    )
    @Column(
        name = "error_reason_desc"
    )
    private String errorReasonDesc;
    @Column(
        name = "report_date"
    )
    private Date reportDate;
    @Size(
        max = 5
    )
    @Column(
        name = "report_status"
    )
    private String reportStatus;
    @Size(
        max = 250
    )
    @Column(
        name = "report_description"
    )
    private String reportDescription;
    @Size(
        max = 150
    )
    @Column(
        name = "report_issuer"
    )
    private String reportIssuer;
    @Size(
        max = 150
    )
    @Column(
        name = "report_provider"
    )
    private String reportProvider;
    @Size(
        max = 150
    )
    @Column(
        name = "report_reference"
    )
    private String reportReference;
    @Size(
        max = 50
    )
    @Column(
        name = "report_batch"
    )
    private String reportBatch;
    @NotNull
    @Column(
        name = "report_retrycount",
        nullable = false
    )
    private Long reportRetrycount;
    @Column(
        name = "settlement_date"
    )
    private Date settlementDate;
    @Size(
        max = 250
    )
    @Column(
        name = "settlement_batch"
    )
    private String settlementBatch;
    @Size(
        max = 250
    )
    @Column(
        name = "settlement_status"
    )
    private String settlementStatus;
    @Size(
        max = 250
    )
    @Column(
        name = "settlement_description"
    )
    private String settlementDescription;
    @NotNull
    @Column(
        name = "settlement_retrycount",
        nullable = false
    )
    private Long settlementRetrycount;
    @Column(
        name = "amount_remaining"
    )
    private Long amountRemaining;
    @Column(
        name = "amount_reversal"
    )
    private Long amountReversal;
    @Column(
        name = "amount_cancel"
    )
    private Long amountCancel;
    @Column(
        name = "cam_agreementowneroid"
    )
    private Long camAgreementowneroid;
    @Column(
        name = "cam_conceptoid"
    )
    private Long camConceptoid;
    @Column(
        name = "cam_legalowneroid"
    )
    private Long camLegalowneroid;
    @Column(
        name = "cam_merchantgroupoid"
    )
    private Long camMerchantgroupoid;
    @Column(
        name = "cam_merchantoid"
    )
    private Long camMerchantoid;
    @Column(
        name = "cam_issueroid"
    )
    private Long camIssueroid;
    @Column(
        name = "cam_issuerconceptoid"
    )
    private Long camIssuerconceptoid;
    @Column(
        name = "cam_issuergroupoid"
    )
    private Long camIssuergroupoid;
    @Column(
        name = "cam_agreement_oid"
    )
    private Long camAgreementOid;
    @Size(
        max = 250
    )
    @Column(
        name = "psp_transaction_id"
    )
    private String pspTransactionId;
    @Size(
        max = 250
    )
    @Column(
        name = "psp_session_id"
    )
    private String pspSessionId;
    @Size(
        max = 2000
    )
    @Column(
        name = "description"
    )
    private String description;
    @Size(
        max = 250
    )
    @Column(
        name = "merchant_name"
    )
    private String merchantName;
    @Size(
        max = 250
    )
    @Column(
        name = "external_merchant_id"
    )
    private String externalMerchantId;
    @Column(
        name = "cam_processor_oid"
    )
    private Long camProcessorOid;
    @Size(
        max = 250
    )
    @Column(
        name = "agreement_id"
    )
    private String agreementId;

    public Transaction() {
    }

    public Long getOid() {
        return this.oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getClientTxId() {
        return this.clientTxId;
    }

    public void setClientTxId(String clientTxId) {
        this.clientTxId = clientTxId;
    }

    public String getClientSessionId() {
        return this.clientSessionId;
    }

    public void setClientSessionId(String clientSessionId) {
        this.clientSessionId = clientSessionId;
    }

    public Long getType() {
        return this.type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getTxRef() {
        return this.txRef;
    }

    public void setTxRef(String txRef) {
        this.txRef = txRef;
    }

    public String getAuthcode() {
        return this.authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public Long getCardInfoOid() {
        return this.cardInfoOid;
    }

    public void setCardInfoOid(Long cardInfoOid) {
        this.cardInfoOid = cardInfoOid;
    }

    public Long getCardInfoParentOid() {
        return this.cardInfoParentOid;
    }

    public void setCardInfoParentOid(Long cardInfoParentOid) {
        this.cardInfoParentOid = cardInfoParentOid;
    }

    public String getHashedPan() {
        return this.hashedPan;
    }

    public void setHashedPan(String hashedPan) {
        this.hashedPan = hashedPan;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getCardProductOid() {
        return this.cardProductOid;
    }

    public void setCardProductOid(Long cardProductOid) {
        this.cardProductOid = cardProductOid;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getIssuerOid() {
        return this.issuerOid;
    }

    public void setIssuerOid(Long issuerOid) {
        this.issuerOid = issuerOid;
    }

    public Long getProviderOid() {
        return this.providerOid;
    }

    public void setProviderOid(Long providerOid) {
        this.providerOid = providerOid;
    }

    public Long getCardProfileOid() {
        return this.cardProfileOid;
    }

    public void setCardProfileOid(Long cardProfileOid) {
        this.cardProfileOid = cardProfileOid;
    }

    public String getMerchantid() {
        return this.merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid;
    }

    public String getStoreid() {
        return this.storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getTerminalid() {
        return this.terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public String getAcquirerid() {
        return this.acquirerid;
    }

    public void setAcquirerid(String acquirerid) {
        this.acquirerid = acquirerid;
    }

    public String getCurrencyIso() {
        return this.currencyIso;
    }

    public void setCurrencyIso(String currencyIso) {
        this.currencyIso = currencyIso;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModified() {
        return this.modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCompleted() {
        return this.completed;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
    }

    public String getAdditionalData() {
        return this.additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public Long getEodBatchOid() {
        return this.eodBatchOid;
    }

    public void setEodBatchOid(Long eodBatchOid) {
        this.eodBatchOid = eodBatchOid;
    }

    public Date getCancelled() {
        return this.cancelled;
    }

    public void setCancelled(Date cancelled) {
        this.cancelled = cancelled;
    }

    public String getCancelledBy() {
        return this.cancelledBy;
    }

    public void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public String getClientTxIdCapt() {
        return this.clientTxIdCapt;
    }

    public void setClientTxIdCapt(String clientTxIdCapt) {
        this.clientTxIdCapt = clientTxIdCapt;
    }

    public String getClientTxIdRev() {
        return this.clientTxIdRev;
    }

    public void setClientTxIdRev(String clientTxIdRev) {
        this.clientTxIdRev = clientTxIdRev;
    }

    public String getCaptureClientSessionId() {
        return this.captureClientSessionId;
    }

    public void setCaptureClientSessionId(String captureClientSessionId) {
        this.captureClientSessionId = captureClientSessionId;
    }

    public String getCaptureProcessingCode() {
        return this.captureProcessingCode;
    }

    public void setCaptureProcessingCode(String captureProcessingCode) {
        this.captureProcessingCode = captureProcessingCode;
    }

    public String getCaptureServiceType() {
        return this.captureServiceType;
    }

    public void setCaptureServiceType(String captureServiceType) {
        this.captureServiceType = captureServiceType;
    }

    public String getCaptureFunctionCode() {
        return this.captureFunctionCode;
    }

    public void setCaptureFunctionCode(String captureFunctionCode) {
        this.captureFunctionCode = captureFunctionCode;
    }

    public Boolean getFlag() {
        return this.flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getReversable() {
        return this.reversable;
    }

    public void setReversable(Boolean reversable) {
        this.reversable = reversable;
    }

    public Boolean getOfflineTx() {
        return this.offlineTx;
    }

    public void setOfflineTx(Boolean offlineTx) {
        this.offlineTx = offlineTx;
    }

    public String getClientTerminalRef() {
        return this.clientTerminalRef;
    }

    public void setClientTerminalRef(String clientTerminalRef) {
        this.clientTerminalRef = clientTerminalRef;
    }

    public String getClientTransmissionDate() {
        return this.clientTransmissionDate;
    }

    public void setClientTransmissionDate(String clientTransmissionDate) {
        this.clientTransmissionDate = clientTransmissionDate;
    }

    public String getClientTransmissionDateformat() {
        return this.clientTransmissionDateformat;
    }

    public void setClientTransmissionDateformat(String clientTransmissionDateformat) {
        this.clientTransmissionDateformat = clientTransmissionDateformat;
    }

    public String getStan() {
        return this.stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public Long getErrorReasonCode() {
        return this.errorReasonCode;
    }

    public void setErrorReasonCode(Long errorReasonCode) {
        this.errorReasonCode = errorReasonCode;
    }

    public String getErrorReasonDesc() {
        return this.errorReasonDesc;
    }

    public void setErrorReasonDesc(String errorReasonDesc) {
        this.errorReasonDesc = errorReasonDesc;
    }

    public Date getReportDate() {
        return this.reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportStatus() {
        return this.reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getReportDescription() {
        return this.reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public String getReportIssuer() {
        return this.reportIssuer;
    }

    public void setReportIssuer(String reportIssuer) {
        this.reportIssuer = reportIssuer;
    }

    public String getReportProvider() {
        return this.reportProvider;
    }

    public void setReportProvider(String reportProvider) {
        this.reportProvider = reportProvider;
    }

    public String getReportReference() {
        return this.reportReference;
    }

    public void setReportReference(String reportReference) {
        this.reportReference = reportReference;
    }

    public String getReportBatch() {
        return this.reportBatch;
    }

    public void setReportBatch(String reportBatch) {
        this.reportBatch = reportBatch;
    }

    public Long getReportRetrycount() {
        return this.reportRetrycount;
    }

    public void setReportRetrycount(Long reportRetrycount) {
        this.reportRetrycount = reportRetrycount;
    }

    public Date getSettlementDate() {
        return this.settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getSettlementBatch() {
        return this.settlementBatch;
    }

    public void setSettlementBatch(String settlementBatch) {
        this.settlementBatch = settlementBatch;
    }

    public String getSettlementStatus() {
        return this.settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public String getSettlementDescription() {
        return this.settlementDescription;
    }

    public void setSettlementDescription(String settlementDescription) {
        this.settlementDescription = settlementDescription;
    }

    public Long getSettlementRetrycount() {
        return this.settlementRetrycount;
    }

    public void setSettlementRetrycount(Long settlementRetrycount) {
        this.settlementRetrycount = settlementRetrycount;
    }

    public Long getAmountRemaining() {
        return this.amountRemaining;
    }

    public void setAmountRemaining(Long amountRemaining) {
        this.amountRemaining = amountRemaining;
    }

    public Long getAmountReversal() {
        return this.amountReversal;
    }

    public void setAmountReversal(Long amountReversal) {
        this.amountReversal = amountReversal;
    }

    public Long getAmountCancel() {
        return this.amountCancel;
    }

    public void setAmountCancel(Long amountCancel) {
        this.amountCancel = amountCancel;
    }

    public Long getCamAgreementowneroid() {
        return this.camAgreementowneroid;
    }

    public void setCamAgreementowneroid(Long camAgreementowneroid) {
        this.camAgreementowneroid = camAgreementowneroid;
    }

    public Long getCamConceptoid() {
        return this.camConceptoid;
    }

    public void setCamConceptoid(Long camConceptoid) {
        this.camConceptoid = camConceptoid;
    }

    public Long getCamLegalowneroid() {
        return this.camLegalowneroid;
    }

    public void setCamLegalowneroid(Long camLegalowneroid) {
        this.camLegalowneroid = camLegalowneroid;
    }

    public Long getCamMerchantgroupoid() {
        return this.camMerchantgroupoid;
    }

    public void setCamMerchantgroupoid(Long camMerchantgroupoid) {
        this.camMerchantgroupoid = camMerchantgroupoid;
    }

    public Long getCamMerchantoid() {
        return this.camMerchantoid;
    }

    public void setCamMerchantoid(Long camMerchantoid) {
        this.camMerchantoid = camMerchantoid;
    }

    public Long getCamIssueroid() {
        return this.camIssueroid;
    }

    public void setCamIssueroid(Long camIssueroid) {
        this.camIssueroid = camIssueroid;
    }

    public Long getCamIssuerconceptoid() {
        return this.camIssuerconceptoid;
    }

    public void setCamIssuerconceptoid(Long camIssuerconceptoid) {
        this.camIssuerconceptoid = camIssuerconceptoid;
    }

    public Long getCamIssuergroupoid() {
        return this.camIssuergroupoid;
    }

    public void setCamIssuergroupoid(Long camIssuergroupoid) {
        this.camIssuergroupoid = camIssuergroupoid;
    }

    public Long getCamAgreementOid() {
        return this.camAgreementOid;
    }

    public void setCamAgreementOid(Long camAgreementOid) {
        this.camAgreementOid = camAgreementOid;
    }

    public String getPspTransactionId() {
        return this.pspTransactionId;
    }

    public void setPspTransactionId(String pspTransactionId) {
        this.pspTransactionId = pspTransactionId;
    }

    public String getPspSessionId() {
        return this.pspSessionId;
    }

    public void setPspSessionId(String pspSessionId) {
        this.pspSessionId = pspSessionId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getExternalMerchantId() {
        return this.externalMerchantId;
    }

    public void setExternalMerchantId(String externalMerchantId) {
        this.externalMerchantId = externalMerchantId;
    }

    public Long getCamProcessorOid() {
        return this.camProcessorOid;
    }

    public void setCamProcessorOid(Long camProcessorOid) {
        this.camProcessorOid = camProcessorOid;
    }

    public String getAgreementId() {
        return this.agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }
}

