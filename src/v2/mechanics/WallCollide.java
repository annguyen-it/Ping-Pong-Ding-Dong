package v2.mechanics;

//  We just care about vertical axis
interface WallCollide extends Collide {

    boolean willWallCollide();
    void wallCollide();
}
