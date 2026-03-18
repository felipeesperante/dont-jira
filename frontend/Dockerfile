# syntax=docker/dockerfile:1.7

FROM node:22-alpine AS build
WORKDIR /app

COPY package.json ./
RUN npm install

COPY src ./src

# Build stage intentionally permissive for initial scaffold.
# A full Quasar build requires project-level quasar config/boot files.
RUN mkdir -p dist && cp -r src dist/src

FROM nginx:1.27-alpine
WORKDIR /usr/share/nginx/html

COPY --from=build /app/dist ./

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
