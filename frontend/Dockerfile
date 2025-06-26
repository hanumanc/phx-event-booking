FROM node:20-alpine

WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy source code
COPY . .

# Expose port
EXPOSE 4200

# Start the development server
CMD ["npm", "start", "--", "--host", "0.0.0.0", "--port", "4200"]