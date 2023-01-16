import { Component } from '@angular/core';
import { FileUploadService } from "../file-upload.service";
import { HttpEventType } from "@angular/common/http";

@Component({
  selector: 'app-input-mult-files',
  templateUrl: './input-mult-files.component.html',
  styleUrls: ['./input-mult-files.component.scss'],
})
export class InputMultFilesComponent {

  constructor(private fileUploadService: FileUploadService) {}

  files: File[] = [];
  loading: Boolean = false;

  onXmlInputChangeHandler(fileInputEvent: any) {
    let filesToAdd: File[] = Array.from(fileInputEvent.target.files);
    this.files.push(...filesToAdd);
  }

  onRemoveFileClickHandler(idxFileToRemove: number) {
    this.files.splice(idxFileToRemove, 1);
  }

  onSendClickHandler() {
    this.loading = true;
    let confirmationCount = 0;
    this.files.forEach(file => {
      this.fileUploadService.upload(file).subscribe(event => {
        if (event.type === HttpEventType.Response) {
          confirmationCount++;
          if (confirmationCount === this.files.length) {
            this.loading = false;
          }
        }
      })
    });
  }
}
