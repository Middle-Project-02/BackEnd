# 1단계: Gradle 빌드 스테이지
FROM gradle:8.13-jdk17 AS build

WORKDIR /app

# Gradle 캐시 최적화를 위해 설정 파일 먼저 복사
COPY build.gradle settings.gradle ./
COPY gradle gradle
COPY gradlew ./

# 의존성 미리 다운로드 (실패 시 빌드 중단)
RUN ./gradlew dependencies

# 전체 프로젝트 복사
COPY . .

# 빌드 (테스트 제외)
RUN ./gradlew bootJar -x test

# 2단계: 실행용 이미지
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/build/libs/together-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
