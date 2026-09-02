import React, { useRef, useState } from 'react';

const API_URL = 'http://localhost:8080/api/snakes/identify';

const ImageUploader = () => {
  const [preview, setPreview] = useState(null);
  const [fileName, setFileName] = useState(null);
  const [selectedFile, setSelectedFile] = useState(null);
  const [isDragging, setIsDragging] = useState(false);
  const [isIdentifying, setIsIdentifying] = useState(false);
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);
  const inputRef = useRef(null);

  const handleFile = (file) => {
    if (!file || !file.type.startsWith('image/')) return;
    setFileName(file.name);
    setSelectedFile(file);
    setResult(null);
    setError(null);
    const reader = new FileReader();
    reader.onload = (e) => setPreview(e.target.result);
    reader.readAsDataURL(file);
  };

  const onDrop = (e) => {
    e.preventDefault();
    setIsDragging(false);
    const file = e.dataTransfer.files?.[0];
    handleFile(file);
  };

  const onSelect = (e) => {
    const file = e.target.files?.[0];
    handleFile(file);
  };

  const clearImage = (e) => {
    e.stopPropagation();
    setPreview(null);
    setFileName(null);
    setSelectedFile(null);
    setResult(null);
    setError(null);
    if (inputRef.current) inputRef.current.value = '';
  };

  const identifySpecies = async () => {
    if (!selectedFile) return;
    setIsIdentifying(true);
    setError(null);
    setResult(null);

    try {
      const formData = new FormData();
      formData.append('image', selectedFile);

      const response = await fetch(API_URL, {
        method: 'POST',
        body: formData,
      });

      if (!response.ok) {
        throw new Error('Server returned an error');
      }

      const data = await response.json();
      setResult(data);
    } catch (err) {
      setError('Could not reach the identification service. Is the backend running on port 8080?');
    } finally {
      setIsIdentifying(false);
    }
  };

  return (
    <div className="uploader-wrap">
      <div
        className={`dropzone ${isDragging ? 'dropzone--active' : ''} ${preview ? 'dropzone--filled' : ''}`}
        onClick={() => inputRef.current?.click()}
        onDragOver={(e) => { e.preventDefault(); setIsDragging(true); }}
        onDragLeave={() => setIsDragging(false)}
        onDrop={onDrop}
        role="button"
        tabIndex={0}
        onKeyDown={(e) => { if (e.key === 'Enter' || e.key === ' ') inputRef.current?.click(); }}
      >
        <input
          ref={inputRef}
          type="file"
          accept="image/*"
          onChange={onSelect}
          hidden
        />

        {preview ? (
          <div className="preview-wrap">
            <img src={preview} alt="Selected snake" className="preview-img" />
            <button className="clear-btn" onClick={clearImage} type="button">✕ Remove</button>
          </div>
        ) : (
          <div className="dropzone-empty">
            <svg className="dropzone-icon" width="40" height="40" viewBox="0 0 40 40" fill="none">
              <path d="M20 6 C11 6 6 15 6 22 C6 28 11 33 20 33 C29 33 34 28 34 22 C34 15 29 6 20 6 Z" stroke="currentColor" strokeWidth="1.5" />
              <circle cx="16" cy="18" r="1.4" fill="currentColor" />
              <path d="M12 26 Q20 32 28 26" stroke="currentColor" strokeWidth="1.5" fill="none" />
            </svg>
            <p className="dropzone-title">Drop specimen photo here</p>
            <p className="dropzone-sub">or click to browse — JPG, PNG</p>
          </div>
        )}
      </div>

      {fileName && (
        <div className="file-tag">
          <span className="file-tag-label">FILE</span>
          <span className="file-tag-name">{fileName}</span>
        </div>
      )}

      <button
        className="analyze-btn"
        disabled={!preview || isIdentifying}
        type="button"
        onClick={identifySpecies}
      >
        {isIdentifying ? 'Identifying…' : 'Identify Species →'}
      </button>

      {error && <div className="result-error">{error}</div>}

      {result && result.snake && (
        <div className="result-card">
          {result.stub && (
            <p className="result-stub-note">⚠ Stub result — random pick, real model not connected yet</p>
          )}

          <div className="result-header">
            <span className="result-confidence">{Math.round(result.confidence * 100)}% match</span>
            <span className={`result-danger result-danger--${result.snake.dangerLevel}`}>
              {result.snake.dangerLevel}
            </span>
          </div>

          <h3 className="result-name">{result.snake.commonName}</h3>
          <p className="result-scientific">{result.snake.scientificName}</p>

          <div className="result-grid">
            <div>
              <span className="result-label">Venomous</span>
              <span className="result-value">{result.snake.venomous ? 'Yes' : 'No'}</span>
            </div>
            <div>
              <span className="result-label">Venom Type</span>
              <span className="result-value">{result.snake.venomType}</span>
            </div>
            <div>
              <span className="result-label">Antivenom</span>
              <span className="result-value">{result.snake.antivenomAvailable ? 'Available' : 'Not typical'}</span>
            </div>
          </div>

          <p className="result-description">{result.snake.description}</p>
        </div>
      )}
    </div>
  );
};

export default ImageUploader;
