import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './shared/layout/header/header';
import { FooterComponent } from './shared/layout/footer/footer';

@Component({
  selector: 'app-root',
  imports: [
    CommonModule, 
    RouterOutlet, 
    HeaderComponent, 
    FooterComponent
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class AppComponent {
  protected title = 'Event Booking Platform';
}
