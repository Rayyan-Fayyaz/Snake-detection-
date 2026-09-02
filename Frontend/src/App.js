import React from 'react';
import ImageUploader from './components/ImageUploader';
import './App.css';

const App = () => {
  return (
    <div className="page">
      <div className="crosshair crosshair--tl" aria-hidden="true" />
      <div className="crosshair crosshair--tr" aria-hidden="true" />
      <div className="crosshair crosshair--bl" aria-hidden="true" />
      <div className="crosshair crosshair--br" aria-hidden="true" />

      <main className="hero">
        <span className="eyebrow">FIELD IDENTIFICATION TOOL</span>
        <h1 className="title">Snake Bite Detection</h1>
        <p className="subhead">
          Upload a photo of the snake. We'll identify the species and flag what you need to know.
        </p>

        <ImageUploader />
      </main>

      <footer className="foot-note">
        Not a substitute for emergency medical care. If bitten, seek help immediately.
      </footer>
    </div>
  );
};

export default App;
