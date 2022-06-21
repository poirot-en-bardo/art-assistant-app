export class GetFavouriteArtworks {
  static readonly type = '[FavouriteArtworks] Get Favourite Artworks'
  constructor() {
  }
}

export class AddFavouriteArtwork {
  static readonly type = '[FavouriteArtworks] Add Favourite'
  constructor(readonly id: number) {
  }
}

export class RemoveFavouriteArtwork {
  static readonly type = '[FavouriteArtworks] RemoveFavourite'
  constructor(readonly id: number) {
  }
}

