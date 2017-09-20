package com.example.myapplication.painting;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SimpleShapesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    class DrawView extends View {

        Paint p;
        RectF rectf;
        float[] points;
        float[] points1;

        Rect rect;
        StringBuilder sb;

        int y = 50;

        Path path;
        Path path1;

        Point point1;
        Point point21;
        Point point22;

        String text;

        public DrawView(Context context) {
            super(context);
            p = new Paint(Paint.ANTI_ALIAS_FLAG);
            rectf = new RectF();
            points = new float[]{100, 50, 150, 100, 150, 200, 50, 200, 50, 100};
            points1 = new float[]{200, 100, 500, 100, 200, 200, 500, 200, 300, 0, 300, 300, 400, 0, 400, 300};

            rect = new Rect(100,200,200,300);
            sb = new StringBuilder();

            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);

            rectf = new RectF(350,100,400,150);
            path = new Path();
            path1 = new Path();

            point1 = new Point(200,300);
            point21 = new Point(200,600);
            point22 = new Point(450,300);

            text = "Draw the text, with origin at (x,y), using the specified paint";
        }

        @Override
        protected void onDraw(Canvas canvas) {
            rectf.set(50, 350, 200, 450);
            canvas.drawARGB(80, 102, 204, 255);

            // черный
            path.reset();
            path.addCircle(130, 150, 100, Path.Direction.CW);
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.BLACK);

            p.setTextSize(20);

            canvas.drawTextOnPath(text, path, 0, 0, p);
            p.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawPath(path, p);

            path.reset();
            path.addCircle(400, 150, 100, Path.Direction.CCW);

            // синий
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.BLUE);
            canvas.drawTextOnPath(text, path, 0, 0, p);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, p);

            // зеленый
            path.offset(-270, 250);
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.GREEN);
            canvas.drawTextOnPath(text, path, 100, 0, p);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, p);

            // красный
            path.offset(270, 0);
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.RED);
            canvas.drawTextOnPath(text, path, 0, 30, p);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, p);

            //--------------------------------------------------------------------------------------
//            // первая линия
//            p.setColor(Color.BLACK);
//            canvas.drawLine(100, 100, 500, 100, p);
//
//            // точка отклонения для первой линии
//            p.setStyle(Paint.Style.FILL);
//            p.setColor(Color.GREEN);
//            canvas.drawCircle(point1.x, point1.y, 10, p);
//
//            // квадратичная кривая
//            path.reset();
//            path.moveTo(100, 100);
//            path.quadTo(point1.x, point1.y, 500, 100);
//            p.setStyle(Paint.Style.STROKE);
//            canvas.drawPath(path, p);
//
//
//            // вторая линия
//            p.setColor(Color.BLACK);
//            canvas.drawLine(100, 400, 500, 400, p);
//
//            // точки отклонения для второй линии
//            p.setStyle(Paint.Style.FILL);
//            p.setColor(Color.BLUE);
//            canvas.drawCircle(point21.x, point21.y, 10, p);
//            canvas.drawCircle(point22.x, point22.y, 10, p);
//
//            // кубическая кривая
//            path.reset();
//            path.moveTo(100, 400);
//            path.cubicTo(point21.x, point21.y, point22.x, point22.y, 500, 400);
//            p.setStyle(Paint.Style.STROKE);
//            canvas.drawPath(path, p);
//--------------------------------------------------------------------------------------------------
//
//            // очистка path
//            path.reset();
//
//            // угол
//            path.moveTo(100, 100);
//            path.lineTo(150, 200);
//            path.lineTo(50, 200);
//
//            // треугольник
//            path.moveTo(250, 100);
//            path.lineTo(300, 200);
//            path.lineTo(200, 200);
//            path.close();
//
//            // квадрат и круг
//            path.addRect(rectf, Path.Direction.CW);
//            path.addCircle(450, 150, 25, Path.Direction.CW);
//
//            // рисование path
//            p.setColor(Color.BLACK);
//            canvas.drawPath(path, p);
//
//
//            // очистка path1
//            path1.reset();
//
//            // две пересекающиеся линии
//            path1.moveTo(50,50);
//            path1.lineTo(500,250);
//            path1.moveTo(500,50);
//            path1.lineTo(50,250);
//
//            // рисование path1
//            p.setColor(Color.GREEN);
//            canvas.drawPath(path1, p);
//
//
//            // добавление path1 к path
//            path.addPath(path1);
//
//            // смещение
//            path.offset(500,100);
//
//            // рисование path
//            p.setColor(Color.BLUE);
//            canvas.drawPath(path, p);
//--------------------------------------------------------------------------------------------------
//            rect.set(75,220,175,320);
//
//            p.setColor(Color.RED);
//            p.setStrokeWidth(10);
//
//            // рисуем точки их массива points
//            canvas.drawPoints(points, p);
//
//            // рисуем линии по точкам из массива points1
//            canvas.drawLines(points1, p);
//
//            // перенастраиваем кисть на зеленый цвет
//            p.setColor(Color.GREEN);
//
//            // рисуем закругленный прямоугольник по координатам из rectf
//            // радиусы закругления = 20
//            canvas.drawRoundRect(rectf, 20, 20, p);
//
//            // смещаем коорднаты rectf на 150 вниз
//            rectf.offset(0, 170);
//            // рисуем овал внутри прямоугольника rectf
//            canvas.drawOval(rectf, p);
//
//            // смещаем rectf в (900,100) (левая верхняя точка)
//            rectf.offsetTo(300, 350);
//            // увеличиваем rectf по вертикали на 25 вниз и вверх
//            rectf.inset(0, -25);
//            // рисуем дугу внутри прямоугольника rectf
//            // с началом в 90, и длиной 270
//            // соединение крайних точек через центр
//            canvas.drawArc(rectf, 25, 320, true, p);
//
//            // смещаем коорднаты rectf на 150 вниз
//            rectf.offset(0, 170);
//            // рисуем дугу внутри прямоугольника rectf
//            // с началом в 90, и длиной 270
//            // соединение крайних точек напрямую
//            canvas.drawArc(rectf, 90, 270, false, p);
//
//            // перенастраиваем кисть на толщину линии = 3
//            p.setStrokeWidth(3);
//            // рисуем линию (150,450) - (150,600)
//            canvas.drawLine(150, 650, 150, 800, p);
//
//            // перенастраиваем кисть на синий цвет
//            p.setColor(Color.BLUE);
//
//            // настраиваем размер текста = 30
//            p.setTextSize(35);
//            // рисуем текст в точке (150,500)
//            p.setTextAlign(Paint.Align.LEFT);
//            canvas.drawText("text left", 150, 700, p);
//
//            // настраиваем выравнивание текста на центр
//            p.setTextAlign(Paint.Align.CENTER);
//            // рисуем текст в точке (150,525)
//            canvas.drawText("text center", 150, 725, p);
//
//            // настраиваем выравнивание текста на левое
//            p.setTextAlign(Paint.Align.RIGHT);
//            // рисуем текст в точке (150,550)
//            canvas.drawText("text right", 150, 750, p);
//
//            //падающий круг
//            p.setColor(Color.MAGENTA);
//            p.setStrokeWidth(5);
//            canvas.drawCircle(150, y, 20, p);
//            y++;
//
//            p.setColor(Color.BLUE);
//            p.setStrokeWidth(10);
//
//            p.setTextSize(30);
//
//            // создаем строку с значениями ширины и высоты канвы
//            sb.setLength(0);
//            sb.append("width = ").append(canvas.getWidth()).append(", height = ").append(canvas.getHeight());
//            p.setTextAlign(Paint.Align.LEFT);
////            canvas.drawText(sb.toString(), 100, 30, p);
//            canvas.drawText("width = " + canvas.getWidth() + ", " + "height = " + canvas.getHeight(), 100, 30, p);
//
//            // перенастраивам кисть на заливку
//            p.setStyle(Paint.Style.FILL);
//            canvas.drawRect(rect, p);
//
//            // перенастраивам кисть на контуры
//            p.setStyle(Paint.Style.STROKE);
//            rect.offset(150, 0);
//            canvas.drawRect(rect, p);
//
//            // перенастраивам кисть на заливку + контуры
//            p.setStyle(Paint.Style.FILL_AND_STROKE);
//            rect.offset(150, 0);
//            canvas.drawRect(rect, p);
//
//            invalidate();
        }
    }
}