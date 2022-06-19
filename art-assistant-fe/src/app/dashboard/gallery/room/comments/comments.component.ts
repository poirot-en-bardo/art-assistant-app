import {Component, Input, OnInit} from '@angular/core';
import {BaseComponent} from "../../../../core/components/base/base.component";
import {CommentService} from "../comment.service";
import {takeUntil} from "rxjs";
import {CommentModel} from "../../../../shared/models/comment.model";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss']
})
export class CommentsComponent extends BaseComponent implements OnInit {
  @Input() comments: CommentModel[];
  currentDate = new Date().toDateString();

  constructor(private commentService: CommentService) {
    super();
  }

  ngOnInit(): void {
    console.log(this.comments)
  }

}
