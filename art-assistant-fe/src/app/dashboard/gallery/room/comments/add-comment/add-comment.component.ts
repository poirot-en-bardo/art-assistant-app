import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Select, Store} from '@ngxs/store';
import {ArtworkModel} from "../../../../../shared/models/artwork.model";
import {BaseComponent} from "../../../../../core/components/base/base.component";
import {CommentForm} from "./comment-form";
import {CommentModel} from "../../../../../shared/models/comment.model";
import {UserState} from "../../../../../shared/redux/user/user.state";
import {Observable, takeUntil} from "rxjs";
import {AuthoriseResponseModel} from "../../../../../shared/models/authorise-response.model";
import {GetLoggedUser} from "../../../../../shared/redux/user/user.action";
import {CommentService} from "../../comment.service";
import {CommentRequestModel} from "../../../../../shared/models/comment-request.model";


@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.scss']
})

export class AddCommentComponent extends BaseComponent implements OnInit {

  @Select(UserState.getLoggedUser)
  private loggedUser$: Observable<AuthoriseResponseModel>;
  loggedUser: AuthoriseResponseModel;

  public commentForm: FormGroup;
  @Input() artwork: ArtworkModel;
  @Output() commentEvent= new EventEmitter<CommentModel>();
  @ViewChild("inputComment") inputComment: ElementRef;



  constructor(private formBuilder: FormBuilder, private store: Store, private commentService: CommentService) {
    super();
  }

  public get CommentForm(): typeof CommentForm {
    return  CommentForm;
  }

  ngOnInit(): void {
    this.store.dispatch(new GetLoggedUser());
    this.loggedUser$.pipe(takeUntil(this.unsubscribe$)).subscribe((userModel) => {
      if (userModel) {
        this.loggedUser = userModel;
      }
    });
    this.commentForm = this.formBuilder.group({
        [CommentForm.MESSAGE]: ['', Validators.required]
      }
    )
  }

  addComment(): void {

    let newComment: CommentRequestModel =
      {
      userId: this.loggedUser.id,
      text: this.commentForm.getRawValue().message,
      artworkId: this.artwork.id
    }
    this.commentService.addComment(newComment).pipe(takeUntil(this.unsubscribe$)).subscribe((response) => {
      console.log(response);
      let postComment: CommentModel = {
        id: response.id,
        text: response.text,
        createdAt: response.createdAt,
        userId: this.loggedUser.id,
        userFirstName: this.loggedUser.firstName,
        userLastName: this.loggedUser.lastName
      }
      this.commentEvent.emit(postComment);
    });

  }

  reset() {
    this.inputComment.nativeElement.value = '';
  }
}
