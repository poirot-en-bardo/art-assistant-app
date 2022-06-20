import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {CommentModel} from "../../../../shared/models/comment.model";
import {FileChangeBaseComponent} from "../../../../core/components/base/file-change-base.component";
import {ArtworkModel} from "../../../../shared/models/artwork.model";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss']
})
export class CommentsComponent extends FileChangeBaseComponent implements OnInit {
  @Input() comments: CommentModel[];
  @Input() artwork: ArtworkModel;

  @ViewChild("inputComment") inputComment: ElementRef;




  constructor() {
    super();
  }

  ngOnInit(): void {
    console.log(this.comments)
  }


  postComment(comment: CommentModel): void {
    this.comments.unshift(comment);
  }


}
