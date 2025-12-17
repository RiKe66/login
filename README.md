# Spring Boot User Management Demo

本プロジェクトは、Spring Boot と MySQL（Docker）を使用したシンプルなユーザー管理アプリケーションです。
学習用およびポートフォリオ提出用として作成しています。

---

## 環境要件

* Java 17 以上
* Maven
* Docker / Docker Desktop
* MySQL 8.x（Docker コンテナ）

---

## 起動手順

### 1. MySQL（Docker）起動

```bash
docker run -d \
  --name mysql-demo \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=your_password \
  mysql:8.0
```

### 2. データベース初期化

プロジェクト直下の `sql/login.sql` を実行します。

```bash
mysql -u root -p < sql/login.sql
```

### 3. アプリケーション設定

`src/main/resources/application.yml` にて
データベース接続情報を設定してください。

### 4. アプリケーション起動

```bash
mvn spring-boot:run
```

---

## 生成AI使用箇所

* README の構成整理
* SQL テーブル設計のレビュー
* Spring Boot の基本構成検討
* フロントエンド画面（HTML）の雛形生成

※ バックエンド処理（認証・認可、業務ロジック）は内容を理解した上で実装しています。

---

## 簡単な設計説明

* Spring Boot を用いた Web アプリケーション構成
* Controller / Service / Mapper（MyBatis）によるレイヤード構成
* JWT を用いた認証方式を採用し、ログイン成功時にトークンを発行
* `LoginInterceptor` にてトークン検証を行い、認証済みリクエストのみを許可
* `GlobalExceptionHandler` による例外の一元管理
* データベース定義は `sql/login.sql` にて管理
* フロントエンドは `static/index.html` / `home.html` を使用

---

以上
