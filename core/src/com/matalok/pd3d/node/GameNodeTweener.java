/*
 * Pixel Dungeon 3D
 * Copyright (C) 2016-2018 Alex Fomins
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

//------------------------------------------------------------------------------
package com.matalok.pd3d.node;

//-------------------------------------------------------------------------------
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.matalok.pd3d.level.object.LevelObject;

import aurelienribon.tweenengine.TweenAccessor;

//------------------------------------------------------------------------------
public class GameNodeTweener 
  implements TweenAccessor<GameNode> {
    //**************************************************************************
    // Callback
    //**************************************************************************
    public static abstract class Callback {
        public abstract void OnFinised(GameNode node);
    }

    //**************************************************************************
    // GameNodeTweener
    //**************************************************************************
    public static final int POS       = 1;
    public static final int ROT       = 2;
    public static final int ALPHA     = 3;
    public static final int SCALE     = 4;
    public static final int ELEVATION = 5;

    //--------------------------------------------------------------------------
    @Override public int getValues(GameNode target, int type, float[] ret) {
        int num = 0;
        switch(type) {
        //......................................................................
        case POS: {
            Vector3 v = target.GetLocalPos();
            ret[0] = v.x; ret[1] = v.y; ret[2] = v.z;
            num = 3;
        } break;

        //......................................................................
        case ROT: {
            Quaternion q = target.GetLocalRot();
            ret[0] = q.x; ret[1] = q.y; 
            ret[2] = q.z; ret[3] = q.w;
            num = 4;
        } break;

        //......................................................................
        case ALPHA: {
            ret[0] = target.GetLocalAlpha();
            num = 1;
        } break;

        //......................................................................
        case SCALE: {
            Vector3 v = target.GetLocalScale();
            ret[0] = v.x; ret[1] = v.y; ret[2] = v.z;
            num = 3;
        } break;

        //......................................................................
        case ELEVATION: {
            ret[0] = ((LevelObject)target).GetElevation();
            num = 1;
        } break; }
        return num;
    }

    //--------------------------------------------------------------------------
    @Override public void setValues(GameNode target, int type, float[] vals) {
        switch(type) {
        //......................................................................
        case POS: {
            target.GetLocalPos(true).set(vals);
        } break;

        //......................................................................
        case ROT: {
            target.GetLocalRot(true).set(
              vals[0], vals[1], vals[2], vals[3]);
        } break;

        //......................................................................
        case ALPHA: {
            target.SetLocalAlpha(vals[0]);
        } break;

        //......................................................................
        case SCALE: {
            target.GetLocalScale(true).set(vals);
        } break;

        //......................................................................
        case ELEVATION: {
            ((LevelObject)target).SetElevation(vals[0]);
        } break; }
    }
}
