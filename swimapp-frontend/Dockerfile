# Step 1
FROM node:22 AS builder

WORKDIR /app

COPY . .

RUN npm install

RUN npm run build

# Step 2
FROM nginx:alpine

COPY --from=builder /app/dist/swimapp-frontend/browser /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
