/**
 * Copyright (c) 2014, Airbitz Inc
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms are permitted provided that 
 * the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer. 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Redistribution or use of modified source code requires the express written
 *    permission of Airbitz Inc.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies, 
 * either expressed or implied, of the Airbitz Project.
 */

/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.airbitz.api;

public class tABC_RequestInfo {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected tABC_RequestInfo(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(tABC_RequestInfo obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        coreJNI.delete_tABC_RequestInfo(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setSzID(String value) {
    coreJNI.tABC_RequestInfo_szID_set(swigCPtr, this, value);
  }

  public String getSzID() {
    return coreJNI.tABC_RequestInfo_szID_get(swigCPtr, this);
  }

  public void setTimeCreation(SWIGTYPE_p_int64_t value) {
    coreJNI.tABC_RequestInfo_timeCreation_set(swigCPtr, this, SWIGTYPE_p_int64_t.getCPtr(value));
  }

  public SWIGTYPE_p_int64_t getTimeCreation() {
    return new SWIGTYPE_p_int64_t(coreJNI.tABC_RequestInfo_timeCreation_get(swigCPtr, this), true);
  }

  public void setPDetails(tABC_TxDetails value) {
    coreJNI.tABC_RequestInfo_pDetails_set(swigCPtr, this, tABC_TxDetails.getCPtr(value), value);
  }

  public tABC_TxDetails getPDetails() {
    long cPtr = coreJNI.tABC_RequestInfo_pDetails_get(swigCPtr, this);
    return (cPtr == 0) ? null : new tABC_TxDetails(cPtr, false);
  }

  public void setAmountSatoshi(SWIGTYPE_p_int64_t value) {
    coreJNI.tABC_RequestInfo_amountSatoshi_set(swigCPtr, this, SWIGTYPE_p_int64_t.getCPtr(value));
  }

  public SWIGTYPE_p_int64_t getAmountSatoshi() {
    return new SWIGTYPE_p_int64_t(coreJNI.tABC_RequestInfo_amountSatoshi_get(swigCPtr, this), true);
  }

  public void setOwedSatoshi(SWIGTYPE_p_int64_t value) {
    coreJNI.tABC_RequestInfo_owedSatoshi_set(swigCPtr, this, SWIGTYPE_p_int64_t.getCPtr(value));
  }

  public SWIGTYPE_p_int64_t getOwedSatoshi() {
    return new SWIGTYPE_p_int64_t(coreJNI.tABC_RequestInfo_owedSatoshi_get(swigCPtr, this), true);
  }

  public tABC_RequestInfo() {
    this(coreJNI.new_tABC_RequestInfo(), true);
  }

}
