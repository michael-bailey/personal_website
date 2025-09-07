# syntax=docker.io/docker/dockerfile:1
FROM eclipse-temurin:21-jdk AS base

WORKDIR /setup

# Install Python, build tools, Node.js and npm
RUN apt-get update && apt-get install -y curl python3 make g++ && \
    curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs && \
    rm -rf /var/lib/apt/lists/*

# run build
FROM base AS build

WORKDIR /build

COPY . .

RUN ./gradlew :frontend:RunInstall
WORKDIR /build/frontend
RUN npm rebuild lightningcss --build-from-source || true
WORKDIR /build
RUN ./gradlew :frontend:RunBuild

FROM node:20-slim AS built

WORKDIR /app
COPY --from=build /build/frontend/.next/standalone /app
COPY --from=build /build/frontend/public /app/public
COPY --from=build /build/frontend/.next/static /app/.next/static

RUN npm install

# Production image, copy all the files and run next
FROM built AS exec
WORKDIR /app

ENV NODE_ENV=production
# Uncomment the following line in case you want to disable telemetry during runtime.
ENV NEXT_TELEMETRY_DISABLED=1

EXPOSE 3000
ENV HOST=0.0.0.0
ENV PORT=3000

# server.js is created by next build from the standalone output
# https://nextjs.org/docs/pages/api-reference/config/next-config-js/output
CMD ["node", "./server.js"]