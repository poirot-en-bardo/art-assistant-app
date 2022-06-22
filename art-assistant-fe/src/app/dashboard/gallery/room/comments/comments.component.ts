import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {CommentModel} from "../../../../shared/models/comment.model";
import {FileChangeBaseComponent} from "../../../../core/components/base/file-change-base.component";
import {ArtworkModel} from "../../../../shared/models/artwork.model";
import {CommentService} from "../comment.service";
import {Observable, takeUntil} from "rxjs";
import {BaseComponent} from "../../../../core/components/base/base.component";
import {Select, Store} from "@ngxs/store";
import {UserState} from "../../../../shared/redux/user/user.state";
import {AuthoriseResponseModel} from "../../../../shared/models/authorise-response.model";
import {GetLoggedUser} from "../../../../shared/redux/user/user.action";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss']
})
export class CommentsComponent extends BaseComponent implements OnInit {

  @Select(UserState.getLoggedUser)
  private loggedUser$: Observable<AuthoriseResponseModel>;
  loggedUser: AuthoriseResponseModel;

  @Input() comments: CommentModel[];
  @Input() artwork: ArtworkModel;

  @ViewChild("inputComment") inputComment: ElementRef;


  constructor(private store: Store, private commentService: CommentService) {
    super();
  }

  ngOnInit(): void {
    this.store.dispatch(new GetLoggedUser());
    this.loggedUser$.pipe(takeUntil(this.unsubscribe$)).subscribe((userModel) => {
      if (userModel) {
        this.loggedUser = userModel;
      }
    });
  }


  postComment(comment: CommentModel): void {
    this.comments.unshift(comment);
  }

  deleteComment(id: number): void {
    if (confirm('Are you sure you want to delete the comment?')) {
      this.commentService.deleteComment(id).pipe(takeUntil(this.unsubscribe$)).subscribe(() => {
          this.comments = this.comments.filter((item) => {
            return item.id !== id;
          })
        }
      )
    }
  }

}
