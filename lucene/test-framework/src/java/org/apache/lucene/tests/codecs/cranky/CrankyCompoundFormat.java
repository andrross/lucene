/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.lucene.tests.codecs.cranky;

import java.io.IOException;
import java.util.Random;
import org.apache.lucene.codecs.CompoundDirectory;
import org.apache.lucene.codecs.CompoundFormat;
import org.apache.lucene.index.SegmentInfo;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.IOContext;

class CrankyCompoundFormat extends CompoundFormat {
  CompoundFormat delegate;
  Random random;

  CrankyCompoundFormat(CompoundFormat delegate, Random random) {
    this.delegate = delegate;
    this.random = random;
  }

  @Override
  public CompoundDirectory getCompoundReader(Directory dir, SegmentInfo si) throws IOException {
    return delegate.getCompoundReader(dir, si);
  }

  @Override
  public void write(Directory dir, SegmentInfo si, IOContext context) throws IOException {
    if (random.nextInt(100) == 0) {
      throw new IOException("Fake IOException from CompoundFormat.write()");
    }
    delegate.write(dir, si, context);
  }
}
