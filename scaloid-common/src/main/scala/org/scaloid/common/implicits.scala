/* 
 *
 * 
 *
 *
 * Less painful Android development with Scala
 *
 * http://scaloid.org
 *
 *
 *
 *
 *
 *
 * Copyright 2013 Sung-Ho Lee
 *
 * Sung-Ho Lee licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

/*
 * This file is automatically generated. Any changes on this file will be OVERWRITTEN!
 * To learn how to contribute, please refer to:
 * https://github.com/pocorall/scaloid/wiki/Inside-Scaloid
 */

package org.scaloid.common

import android.content._
import android.graphics.Movie
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view._
import language.implicitConversions

private[scaloid] class UnitConversion(val ext: Double)(implicit context: Context) {
  def dip: Int = (ext * context.getResources().getDisplayMetrics().density).toInt
  def sp : Int = (ext * context.getResources().getDisplayMetrics().scaledDensity).toInt
}

private[scaloid] class ResourceConversion(val id: Int)(implicit context: Context) {
  def r2Text         : CharSequence        = context.getText(id)
  def r2TextArray    : Array[CharSequence] = context.getResources.getTextArray(id)
  def r2String       : String              = context.getResources.getString(id)
  def r2StringArray  : Array[String]       = context.getResources.getStringArray(id)
  def r2Drawable     : Drawable            = context.getResources.getDrawable(id)
  def r2Movie        : Movie               = context.getResources.getMovie(id)
}

trait ConversionImplicits {
  @inline implicit def Double2unitConversion(ext: Double)(implicit context: Context): UnitConversion = new UnitConversion(ext)(context)
  @inline implicit def Long2unitConversion  (ext: Long)  (implicit context: Context): UnitConversion = new UnitConversion(ext)(context)
  @inline implicit def Int2unitConversion   (ext: Int)   (implicit context: Context): UnitConversion = new UnitConversion(ext)(context)

  @inline implicit def Int2resource(ext: Int)(implicit context: Context): ResourceConversion = new ResourceConversion(ext)(context)

  // r2String is not provided because it is ambiguous with r2Text
  @inline implicit def r2Text       (id: Int)(implicit context: Context): CharSequence        = context.getText(id)
  @inline implicit def r2TextArray  (id: Int)(implicit context: Context): Array[CharSequence] = context.getResources.getTextArray(id)
  @inline implicit def r2StringArray(id: Int)(implicit context: Context): Array[String]       = context.getResources.getStringArray(id)
  @inline implicit def r2Drawable   (id: Int)(implicit context: Context): Drawable            = context.getResources.getDrawable(id)
  @inline implicit def r2Movie      (id: Int)(implicit context: Context): Movie               = context.getResources.getMovie(id)

  @inline implicit def string2Uri           (str: String): Uri            = Uri.parse(str)
  @inline implicit def string2IntentFilter  (str: String): IntentFilter   = new IntentFilter(str)
}
object ConversionImplicits extends ConversionImplicits

trait InterfaceImplicits {
  implicit def func2ViewOnClickListener[F](f: (View) => F): View.OnClickListener =
    new View.OnClickListener() {
      def onClick(view: View) {
        f(view)
      }
    }

  implicit def lazy2ViewOnClickListener[F](f: => F): View.OnClickListener =
    new View.OnClickListener() {
      def onClick(view: View) {
        f
      }
    }

  implicit def func2DialogOnClickListener[F](f: (DialogInterface, Int) => F): DialogInterface.OnClickListener =
    new DialogInterface.OnClickListener {
      def onClick(dialog: DialogInterface, which: Int) {
        f(dialog, which)
      }
    }

  implicit def lazy2DialogOnClickListener[F](f: => F): DialogInterface.OnClickListener =
    new DialogInterface.OnClickListener {
      def onClick(dialog: DialogInterface, which: Int) {
        f
      }
    }

  implicit def func2runnable[F](f: () => F): Runnable =
    new Runnable() {
      def run() {
        f()
      }
    }

  implicit def lazy2runnable[F](f: => F): Runnable =
    new Runnable() {
      def run() {
        f
      }
    }
}
object InterfaceImpliciits extends InterfaceImplicits

trait ViewImplicits {
  @inline implicit def menu2RichMenu[V <: android.view.Menu](menu: V) = new RichMenu[V](menu)
  @inline implicit def contextMenu2RichContextMenu[V <: android.view.ContextMenu](contextMenu: V) = new RichContextMenu[V](contextMenu)
  @inline implicit def view2RichView[V <: android.view.View](view: V) = new RichView[V](view)
  @inline implicit def viewGroup2RichViewGroup[V <: android.view.ViewGroup](viewGroup: V) = new RichViewGroup[V](viewGroup)
  @inline implicit def surfaceView2RichSurfaceView[V <: android.view.SurfaceView](surfaceView: V) = new RichSurfaceView[V](surfaceView)
  @inline implicit def viewStub2RichViewStub[V <: android.view.ViewStub](viewStub: V) = new RichViewStub[V](viewStub)
}
object ViewImplicits extends ViewImplicits


trait Implicits extends ConversionImplicits with InterfaceImplicits with ViewImplicits with WidgetImplicits
object Implicits extends Implicits
