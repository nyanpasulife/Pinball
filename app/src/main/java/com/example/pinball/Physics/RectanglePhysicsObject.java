package com.example.pinball.Physics;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.pinball.R;

//TODO 엄밀한 정의를 위해 사각형 모양을 사용하는 오브젝트는 이것을 상속하도록 합시다. 한 점, 직선 경계면 등은 GJK 알고리즘으로 작동하는 PolygonPO로 제작 가능합니다. (대신 원 오브젝트에서 따로 원-폴리곤 충돌 검사를 정의해 줘야 합니다.)
public class RectanglePhysicsObject extends PolygonPhysicsObject{
    public RectanglePhysicsObject(Vector2D position, double width, double height, Bitmap bitmap) {
        super(position,width,height,bitmap);
    }
    public RectanglePhysicsObject(Vector2D position, double width, double height, Bitmap bitmap, boolean move) {
        super(position,width,height,bitmap,move);
    }
}
