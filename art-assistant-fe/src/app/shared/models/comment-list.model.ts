import {CommentModel} from "./comment.model";

export interface CommentListModel {
  index: number;
  comments: CommentModel[];
}
