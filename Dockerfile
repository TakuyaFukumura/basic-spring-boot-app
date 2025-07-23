# 実行用の軽量イメージ
FROM eclipse-temurin:21-jre-alpine

# 非rootユーザーでの実行
RUN addgroup -g 1001 spring && adduser -u 1001 -G spring -s /bin/sh -D spring

USER spring:spring

# 作業ディレクトリの設定
WORKDIR /app

# ビルドされたJARファイルをコピー
# 事前に "./mvnw clean package" を実行してJARファイルを作成してください
COPY --chown=spring:spring target/myproject.jar app.jar

# アプリケーションのポート
EXPOSE 8080

# アプリケーションの起動
ENTRYPOINT ["java", "-jar", "app.jar"]
