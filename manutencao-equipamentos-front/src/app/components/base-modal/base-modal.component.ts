import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-base-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './base-modal.component.html',
})
export class BaseModalComponent {
  @Input() isOpen = false;
  @Output() onClose = new EventEmitter<void>();

  openModal() {
    this.isOpen = true;
  }

  closeModal() {
    this.isOpen = false;
    this.onClose.emit();
  }
}
