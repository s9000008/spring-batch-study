# spring-batch-study

### 簡介
Spring Batch 了解其運作原理與使用方法



### 額外嘗試項目:
嘗試同時間執行兩個batch,但並沒有發生BATCH_JOB_INSTANCE ORA-08177: Can not serialize access for this transaction.

### Entity:
Products
| 欄位名稱 | 該欄位說明 |
| --------|-----------|
| id      | 產品編號   |
| productName | 產品名稱 |
| price   | 產品價格   |

Orders
| 欄位名稱 | 該欄位說明 |
| --------|-----------|
| id      | 訂單編號   |
| userId  | 使用者編號 |
| orderDate | 訂單日期 |
| status   | 訂單狀態   |

OrderItem
| 欄位名稱 | 該欄位說明 |
| --------|-----------|
| id      | 訂單明細編號 |
| orderId  | 訂單編號 |
| productId | 產品編號 |
| quantity   | 訂購數量 |
| price   | 訂單明細價格   |
好的，以下是對應欄位的說明與欄位名稱：

| 欄位名稱 | 該欄位說明 |
| --------|-----------|
| id      | 最終訂單編號 |
| userId  | 使用者編號 |
| orderId | 訂單編號 |
| orderDate   | 訂單日期   |
| quantity   | 訂購數量   |
| total_price | 訂單總價   |
| status | 訂單狀態   |

FinalOrder
| 欄位名稱 | 該欄位說明 |
| --------|-----------|
| id      | 最終訂單編號 |
| userId  | 使用者編號 |
| orderId | 訂單編號 |
| orderDate   | 訂單日期   |
| quantity   | 訂購數量   |
| total_price | 訂單總價   |
| status | 訂單狀態   |

ProductReport
| 欄位名稱 | 該欄位說明 |
| --------|-----------|
| id      | 產品報表編號 |
| productId  | 產品編號 |
| quantity | 銷售總數 |
| orderCount   | 訂單數量   |
| price   | 價格   |

### URL
| URL | 簡介 |
|---------|---------|
| /job/demo    | Demo主動觸發該批次任務|
| /job/job1    | 訂單總金額與數量彙整|
| /job/job2    | 產品售出彙整|
