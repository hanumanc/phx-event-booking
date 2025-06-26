export interface Event {
  id?: string;
  title: string;
  description: string;
  vendorId: string;
  category: string;
  location: string;
  venue: string;
  eventDate: Date;
  startTime: Date;
  endTime: Date;
  ticketPrice: number;
  maxAttendees: number;
  currentAttendees?: number;
  status: EventStatus;
  imageUrl?: string;
  createdAt?: Date;
  updatedAt?: Date;
}

export enum EventStatus {
  PENDING = 'PENDING',
  APPROVED = 'APPROVED',
  REJECTED = 'REJECTED',
  CANCELLED = 'CANCELLED',
  COMPLETED = 'COMPLETED'
}