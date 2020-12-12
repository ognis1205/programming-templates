;;; #FILENAME# --- Describe the File -*- lexical-binding: t; -*-
;;; Commentary:
;;; Code:

(use-package #FILENAME_WO_EXTENSION#
  ;; Package Loading Section. :disabled :defer :demand
  :no-require t
  :ensure t
  :disabled
  :defer
  :demand

  ;; Package Conditional Section.
  :if :when :unless
  :defines
  :functions
  :preface

  ;; Package Dependency Section.
  :ensure-system-package
  :load-path
  :quelpa
  :pin
  :requires
  :after

  ;; Package Mode Section.
  :delight
  :interpreter
  :hook
  :magic
  :magic-fallback
  :mode

  ;; Package Command Section.
  :commands
  :bind
  :bind-key

  ;; Package Custom Section.
  :custom
  :custom-face

  ;; Package Configuration Section.
  :init
  :config
  )

(provide '#FILENAME_WO_EXTENSION#)

;;; #FILENAME# ends here
