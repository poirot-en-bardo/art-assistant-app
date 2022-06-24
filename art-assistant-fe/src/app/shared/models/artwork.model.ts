export interface ArtworkModel {
  id: number;
  title: string;
  year: number;
  technique: string;
  description: string;
  imagePath: string;
  roomId: number;
  position: number;
  artistFirstName: string;
  artistLastName: string;
  artistId: number;
  genreId: number;
  genreName: string;
  galleryName: string;
  museumName: string;
  favourite: boolean;
}
